package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.CustomerService;
import com.HM.Hotel.Management.entity.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomertypeController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customerlist")
    public String list(Model model){
        List<CustomerType> list=customerService.findAll();
        model.addAttribute("customerlist",list);
      return  "admin/customer";
    }

    @RequestMapping("/savecustomer")
    public String save(Integer id, String c_type, String dis_attr, int discount, float price , String dis_price, String remark, RedirectAttributes redirectAttributes){
       CustomerType customerType=null;
       if (id==null){
           customerType=new CustomerType();
           customerType.setC_type(c_type);
           customerType.setDis_attr(dis_attr);
           customerType.setDiscount(discount);
           customerType.setPrice(price);
           customerType.setDis_price(dis_price);
           customerType.setRemark(remark);
       }
       else {
           customerType=customerService.finById(id);
           customerType.setC_type(c_type);
           customerType.setDis_attr(dis_attr);
           customerType.setDiscount(discount);
           customerType.setPrice(price);
           customerType.setDis_price(dis_price);
           customerType.setRemark(remark);
       }
       customerType=customerService.save(customerType);
        return "redirect:customerlist";
    }
    @RequestMapping("/editcustomerType")
    public String edit(Integer id,Model model){
        CustomerType customerType=null;
        if (id==null){
            customerType=new CustomerType();
        }else {
            customerType=customerService.finById(id);
        }
        model.addAttribute("customer",customerType);
        return  "admin/customerType_rdit";
    }

    @RequestMapping("deletecustomer")
  public String delete(Integer id){
        customerService.delete(id);
        return "redirect:customerlist";
    }

}
