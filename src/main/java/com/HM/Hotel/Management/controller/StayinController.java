package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.EngageService;
import com.HM.Hotel.Management.Service.LivreinService;
import com.HM.Hotel.Management.Service.RoomInfoService;
import com.HM.Hotel.Management.entity.Engage;
import com.HM.Hotel.Management.entity.Livein;
import com.HM.Hotel.Management.entity.Pwd;
import com.HM.Hotel.Management.entity.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StayinController {

      @Autowired
    EngageService engageService;

      @Autowired
    RoomInfoService roomInfoService;

      @Autowired
    LivreinService livreinService;


    @RequestMapping("stayin")
    public String Stayin(){
        return  "admin/stayin";
    }


    @RequestMapping("/searchphone")    //订单表格资料
    public String search(Model model,String phone){
        Engage engage=engageService.findByPhoneLike("%"+phone+"%");
        if (engage==null){
            System.out.println("记录为空");
            model.addAttribute("message","搜索记录为空,请确定号码是否正确");
        }else {
            List<RoomInfo>  roomInfo=roomInfoService.findByRoomTypeAndRemark(engage.getRTypeId(),"1");
            model.addAttribute("roomInfotype",roomInfo);
            model.addAttribute("engage",engage);
        }
        return  "admin/stayin";
    }

    @RequestMapping("savatostayin")
    public String savatostayin(HttpSession session,Model model, String phones,String rno, String rTypeid, String cname, String sex, String zjType, String foregift, String remark, String require,String name){
        System.out.println("aaa");
        Livein livein=new Livein();
        RoomInfo roomInfo=new RoomInfo();
        Engage engage=new Engage();
        String thname=cname+name;
        Pwd pwd= (Pwd) session.getAttribute("loginUser"); //获取当前操作员名字
            livein.setRNo(rno);
            livein.setRTypeId(rTypeid);
            livein.setCName(cname);
            livein.setSex(sex);
            livein.setZjType(zjType);
            livein.setInTime(new Date());
            livein.setForegift(foregift);
            livein.setRemark(remark);
            livein.setRequire(require);
            livein.setPhones(phones);
            livein.setName(thname);
            livein.setUserid(pwd.getUserid());
           livein=livreinService.save(livein);
           roomInfo= roomInfoService.findByLocation(livein.getRNo()); //根据房间号查询
          engage=engageService.findByPhoneLike(phones); //根据手机号码修改订单状态
           roomInfo.setRemark("0"); //修改状态
           roomInfo.setState("已预订");//修改状态
          roomInfo= roomInfoService.save(roomInfo);
          engage.setState(1);//修改状态
          engage=engageService.save(engage);
        model.addAttribute("message","入住成功");
        return  "admin/stayin";
    }
    @RequestMapping("livinglist")
    public String living(Model model){
        List<Livein> liveins=livreinService.findAll();
        model.addAttribute("livin",liveins);
        return  "admin/living";
    }
    @RequestMapping("/searchphonesorname")
   public  String searchphonesorname(Model model,String phones){
    List<Livein>  livein=livreinService.findByPhonesLike("%"+phones+"%");
        if (livein.isEmpty() ){
            System.out.println("记录为空");
            model.addAttribute("message","搜索记录为空,请确定号码或者姓名是否正确");
        }
        else {
            model.addAttribute("livin",livein);
        }
        System.out.println("-----------------");
        Livein  name=livreinService.findByNameLike("%"+phones+"%");
        if (name==null){
            System.out.println("记录为空");
            model.addAttribute("message","搜索记录为空,请确定号码或者姓名是否正确");
        }
        else {
            model.addAttribute("livin",name);
        }
       return  "admin/living";
   }


}
