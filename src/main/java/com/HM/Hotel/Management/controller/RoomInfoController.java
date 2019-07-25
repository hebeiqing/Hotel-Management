package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.RoomInfoService;
import com.HM.Hotel.Management.Service.RoomTypeService;
import com.HM.Hotel.Management.entity.RoomInfo;
import com.HM.Hotel.Management.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class RoomInfoController {
    @Autowired
    private RoomInfoService roomInfoService;
    @Autowired
    private RoomTypeService roomTypeService;
    @RequestMapping("/roomInfoList")
    public String list(Model model){
       List<RoomInfo> list= roomInfoService.finAll();
        List<RoomType> type=roomTypeService.findAll();
        model.addAttribute("iofo",list);
        model.addAttribute("listtype",type);
        return "admin/roomInfo";
    }

  @RequestMapping("/roonInfosave")
    public  String save(Integer id, String room_type, String state, String location, String r_tel, String img ,MultipartFile file, String remark, String statetime, RedirectAttributes redirectAttributes){
      RoomInfo info=null;
      if (id==null){
          info=new RoomInfo();
          info.setRoomType(room_type);
          info.setState(state);
          info.setLocation(location);
          info.setR_tel(r_tel);
          info.setRemark(remark);
          info.setStatetime(statetime);
          /*info.setR_img(r_img);*/
           if (file.isEmpty()){
               System.out.println("文件为空");
               return "redirect:roomInfoList";
           }
           try{
               byte[] bytes=file.getBytes();
             /*  Path path = Paths.get("E:\\fileUpload/" + file.getOriginalFilename());*/
               Path path = Paths.get("E:\\何北清\\第三阶段项目\\Hotel_Management\\Hotel Management\\src\\main\\resources\\static\\images/" + file.getOriginalFilename());//上传图片
               Files.write(path,bytes);
               info.setR_img(file.getOriginalFilename());
               System.out.println("上传成功");
           }
           catch (Exception e){
               e.printStackTrace();
           }

      }
      else{
          info=roomInfoService.finById(id);
          info.setRoomType(room_type);
          info.setState(state);
          info.setLocation(location);
          info.setR_tel(r_tel);
          info.setR_img(file.getOriginalFilename());
          info.setRemark(remark);
          info.setStatetime(statetime);
          if (file.isEmpty()){
              System.out.println("文件为空");
              info.setRoomType(room_type);
              info.setState(state);
              info.setLocation(location);
              info.setR_tel(r_tel);
              info.setR_img(img);
              info.setRemark(remark);
              info.setStatetime(statetime);
              info=roomInfoService.save(info);
              return "redirect:roomInfoList";
          }
          try{
              byte[] bytes=file.getBytes();
             /* Path path = Paths.get("E:\\fileUpload/" + file.getOriginalFilename());*/
              Path path = Paths.get("E:\\何北清\\第三阶段项目\\Hotel_Management\\Hotel Management\\src\\main\\resources\\static\\images/" + file.getOriginalFilename());//上传图片
              Files.write(path,bytes);
              info.setR_img(file.getOriginalFilename()); //路径添加到数据库
              System.out.println("上传成功");
          }
          catch (Exception e){
              e.printStackTrace();
          }

      }
      info=roomInfoService.save(info);
      redirectAttributes.addFlashAttribute("message","保存用户成功");
      return "redirect:roomInfoList";
  }
  @RequestMapping("/editInfo")
    public String edit(Integer id,Model model){
        RoomInfo Info=null;
      List<RoomType> list=null;
        if (id==null){
            Info=new RoomInfo();
            list=roomTypeService.findAll();
        }else{
            Info=roomInfoService.finById(id);
            list=roomTypeService.findAll();
        }
        model.addAttribute("roominfo",Info);
       model.addAttribute("typelist",list);
        return "admin/Infoedit";
  }
  @RequestMapping("/deleteInfo")
    public String delete(int id,RedirectAttributes redirectAttributes){
        roomInfoService.delete(id);
        redirectAttributes.addFlashAttribute("message","删除用户成功");
        return "redirect:roomInfoList";
    }

  @RequestMapping("/index")
    public String slider(Model model, String roomtype){
        roomtype="总统套间";
        List<RoomInfo> roomInfo=roomInfoService.findByRoom_type(roomtype);
        model.addAttribute("type",roomInfo);
        return "index";

  }
    @RequestMapping("/search")
    public String search(Model model, String roomtype){
          /*  RoomType type=roomTypeService.findByRtype(roomtype);*/
          /*  model.addAttribute("type",type);*/
     /*   if (roomtype==null || roomtype.equals("")){
            return  "room";
        }else {*/
            RoomType type=roomTypeService.findByRType(roomtype);
            List<RoomInfo> roomInfo=roomInfoService.findByRoom_type(roomtype);
            if (roomInfo.isEmpty()){
                model.addAttribute("message","程序员已跑路");
            }
           if (type.getDelmark()==0){
                model.addAttribute("roomnumbr","该"+type.getRType()+"房间已被预订完，请选择其它类型房间");
            }

            model.addAttribute("searchroom",roomInfo);
            model.addAttribute("type",type);
            return "room";

    }

    @RequestMapping("searchroomtype")
public  String searchroomtype(Model model,String type){
        List<RoomInfo> roomInfo=roomInfoService.findByRoom_type(type);
        if (roomInfo.isEmpty()){
            model.addAttribute("message","请确认房间类型是否存在");
        }else {
            model.addAttribute("iofo",roomInfo);
        }
        return "admin/roomInfo";
    }

    @RequestMapping("/roomfinall")
    public String roomlist(Model model){
        List<RoomInfo> list= roomInfoService.finAll();
        model.addAttribute("searchroom",list);
        return "findallroom";
    }
}
