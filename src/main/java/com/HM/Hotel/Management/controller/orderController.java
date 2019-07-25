package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.EngageService;
import com.HM.Hotel.Management.Service.RoomTypeService;
import com.HM.Hotel.Management.entity.Engage;
import com.HM.Hotel.Management.entity.RoomInfo;
import com.HM.Hotel.Management.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Configuration
public class orderController {
    @Autowired
    private EngageService engageService;

    @Autowired
    private RoomTypeService roomTypeService;


    @RequestMapping("/order")
    public String order(Model model,String imgname,String roomtype,String money){
        model.addAttribute("img",imgname);
        model.addAttribute("type",roomtype);
        model.addAttribute("money",money);
        System.out.printf("git修改测试");
        return "order";
    }

    @RequestMapping("torder")
public String torder(Model model,String time,String imgname,String roomtype,double money) throws ParseException {
        int days=daysBetween(time);
        double moneys=days*money;
        model.addAttribute("time",time);
        model.addAttribute("img",imgname);
        model.addAttribute("roomtype",roomtype);
        model.addAttribute("days",days);
        model.addAttribute("money",moneys);
        return "torder";
    }
    @RequestMapping("/searchroomphone")    //订单表格资料
    public String search(Model model,String phone){
        Engage engage=engageService.findByPhoneLike("%"+phone+"%");
        if (engage==null){
            System.out.println("记录为空");
            model.addAttribute("message","搜索记录为空,请确定号码是否正确");
        }else {
            model.addAttribute("engage",engage);
        }
        return "admin/order";
    }

    @RequestMapping("savaengage")
    public  String engages(Model model, Integer id, String surname, String name, String email, String phone, String rTypeId, String paTime, String checkout_time, double money, String  request){
           Engage engage=null;
        String cday=paTime.substring(0,10); //保存入住时间
        String uday=paTime.substring(13,23);//保存退房时间
            if (id==null){
                engage=new Engage();
                engage.setSurName(surname);
                engage.setName(name);
                engage.setEMail(email);
                engage.setPhone(phone);
                engage.setRTypeId(rTypeId);
                engage.setPaTime(cday);
                engage.setChecKoutTtime(uday);
                engage.setMoney(money);
                engage.setReQuest(request);
                engage.setState(0);
            }
           /* else{
                engage=engageService.finById(id);
                engage.setSurName(surname);
                engage.setName(name);
                engage.setEMail(email);
                engage.setPhone(phone);
                engage.setRTypeId(rTypeId);
                engage.setPaTime(cday);
                engage.setChecKoutTtime(uday);
                engage.setMoney(money);
                engage.setChecKoutTtime(checkout_time);
                engage.setReQuest(request);
            }*/
             engage=engageService.save(engage);
             model.addAttribute("engage",engage);
             RoomType roomType=new RoomType();
            if (engage!=null){
                roomType=roomTypeService.findByRType(rTypeId);//查询房间类型
             int roomnuber=roomType.getDelmark();//查询房间类型数量
             if (roomType.getDelmark()>0){  //如果房间数量大于0 ，则减一，少于0则无法预订
                 roomnuber--;
                 roomType.setDelmark(roomnuber); //减一之后把最新的房间数量保存

                 roomType=roomTypeService.save(roomType);
             }else {
               /*  model.addAttribute("roomnumbr","该"+roomType.getRType()+"房间已被预订完，请选择其它类型房间");*/
             }

            }
        return "redirect:index";
    }

    @RequestMapping("/orderlist")
    public String orderlist(Model model){
       List<Engage> list=engageService.findAll();
       model.addAttribute("engage",list);
        return "admin/order";
    }

    public static int daysBetween(String time) throws ParseException {
        String cday=time.substring(0,10);
        String uday=time.substring(13,23);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(cday); //转换为时间格式
        Date date2 = sdf.parse(uday);

        Calendar cal = Calendar.getInstance();

        cal.setTime(date1);

        long time1 = cal.getTimeInMillis();

        cal.setTime(date2);

        long time2 = cal.getTimeInMillis();

        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));

    }


}
