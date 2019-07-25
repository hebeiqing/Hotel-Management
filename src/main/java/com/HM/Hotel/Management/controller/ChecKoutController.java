package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.*;
import com.HM.Hotel.Management.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ChecKoutController {

   @Autowired
    LivreinService livreinService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    EngageService engageService;
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    RoomInfoService roomInfoService;



    @RequestMapping("/checkout")
    public String checkout(){
        return  "admin/checkout";
    }

    @RequestMapping("/searchorder")
    public  String searchphonesorname(Model model,String phone) throws ParseException {
      List<Livein> livein1=livreinService.findByPhonesLike("%"+phone+"%");
        if (livein1.isEmpty()){
           System.out.println("记录为空");
              model.addAttribute("message", "搜索记录为空,请确定号码是否正确");
        }
      else {
          for (Livein livein2 : livein1) {

              //查询房间价格。对比差价
              RoomType RoomType = roomTypeService.findByRType(livein2.getRTypeId());
              Float price = RoomType.getPrice();
              //查询订单信息，查询已付金额以及预定退房时间
              Engage engage = engageService.findByPhoneLike(livein2.getPhones());

              //获取当前系统时间
              Date date = new Date();
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
              String dates = dateFormat.format(date);
              String dates2 = livein2.getInTime().toString();

              //减去其中天数
              int i = daysBetween(dates, dates2);
              //得出应付金额
              double j = i * price;
              //应付金额减去已付金额得出差价
              double money = j - engage.getMoney();

              model.addAttribute("order", livein2); //获取搜索对象
              model.addAttribute("engage", engage); //预计退房当天
              model.addAttribute("date", dates); //结算当天
              model.addAttribute("dates", i);//差别天数
              model.addAttribute("j", j);//应付金额
              model.addAttribute("money", money);//差价

          }
      }
        return  "admin/checkout";
    }

   //结算订单
    @RequestMapping("/savecheckout")
    public  String savecheckout(Model model,String number, int days,float money,String checkTime,String roomcno,String roomtype){
        ChecKout checKout=new ChecKout();
        Engage engage=new Engage();
        RoomInfo roomInfo=new RoomInfo();
        RoomType roomType=new RoomType();
        checKout.setNumber(number);
        checKout.setDays(days);
        checKout.setMoney(money);
        checKout.setCheckTime(checkTime);
       checKout= checkoutService.save(checKout); //保存结算订单
        //更改订单状态
        engage= engageService.findByPhoneLike(number); //根据电话号码查询订单信息
        engage.setState(2); //修改订单状态为退房
        engage=engageService.save(engage);//保存订单状态
       //根据房间号修改房间状态
        roomInfo=roomInfoService.findByLocation(roomcno);
        roomInfo.setRemark("1");//修改房间状态 可预订
       roomInfo=roomInfoService.save(roomInfo);//保存改房间状态
        roomType=roomTypeService.findByRType(roomtype);//查询房间类型
        int roomnuber=roomType.getDelmark();//查询房间类型数量
            if (roomType.getDelmark()>0){  //如果房间数量大于0 ，则加一
                roomnuber++;
                roomType.setDelmark(roomnuber); //加一之后把最新的房间数量保存
                roomType=roomTypeService.save(roomType); //保存最新房间数量
            }
        model.addAttribute("message","退房成功");
        return  "admin/checkout";
    }

   @RequestMapping("/checkoutlist")
   public String checkoutlist(Model model){
        List<ChecKout> checKout=checkoutService.findAll();
        model.addAttribute("checKout",checKout);
        return  "admin/checkoutlist";
   }

    @RequestMapping("searchonumber")
    public  String searchonumber(Model model,String number){
        List<ChecKout> checKout=checkoutService.findByNumberLike("%"+number+"%");
        if (checKout.isEmpty()){
            System.out.println("记录为空");
            model.addAttribute("message","搜索记录为空,请确定号码是否正确");
        }else{
            model.addAttribute("checKout",checKout);
        }

        return  "admin/checkoutlist";
    }







    public static int daysBetween(String uday,String cday) throws ParseException {

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
