package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.RoomTypeService;
import com.HM.Hotel.Management.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping("/footer")
    public  String footer(){
        return "admin/footer";
    }
    @RequestMapping("/head")
    public  String head(){
        return "admin/head";
    }
    @RequestMapping("/foot")
    public  String foot(){
        return "foot";
    }



    @RequestMapping("/indextop")
    public  String indextop(Model model){
        List<RoomType> roomTypeList=roomTypeService.findAll();
        model.addAttribute("type",roomTypeList);
        return "head";
    }

    @RequestMapping("/roomtypelist")
    public  String list(Model model){
        List<RoomType> list=roomTypeService.findAll();
        model.addAttribute("roomtype",list);
        return "admin/roomType";
    }

    @PostMapping("/savaroomtype")
    public  String sava(Integer id,String t_type, String bed, float price, float foregift, String cl_room, float cl_price,String delmark, String remark, RedirectAttributes redirectAttributes){
        RoomType type=null;
        if (id==null){
            type=new RoomType();
            type.setRType(t_type);
            type.setBed(bed);
            type.setPrice(price);
            type.setForegift(foregift);
            type.setCl_room(cl_room);
            type.setCl_price(cl_price);
            type.setRemark(delmark);
            type.setRemark(remark);
        }else{
            type=roomTypeService.finById(id);
            type.setRType(t_type);
            type.setBed(bed);
            type.setPrice(price);
            type.setForegift(foregift);
            type.setCl_room(cl_room);
            type.setCl_price(cl_price);
            type.setRemark(delmark);
            type.setRemark(remark);
        }
        type=roomTypeService.save(type);
        redirectAttributes.addFlashAttribute("message","保存用户成功");
        return "redirect:roomtypelist";
    }

    @GetMapping("/editroomtype")
    public String edit(Integer id, Model model){
        RoomType type=null;
        if (id==null){
            type=new RoomType();
        }else{
            type=roomTypeService.finById(id);
        }
        model.addAttribute("type",type);
        return "admin/roomType_edit";
    }
    /* @RequestMapping("pages")
    public String CityById(int pageNum, Model map){
         System.out.println("分页功能");
        List<RoomType> pege = roomTypeService.pege(pageNum*1, 2);
        int cont = roomTypeService.findCont()/1>1?2:1;
        System.out.println(cont);
        map.addAttribute("placeList",pege);
        map.addAttribute("num",pageNum);
        map.addAttribute("cont",cont);
        return "pages";
    }*/


}
