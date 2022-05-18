package ru.mirea.carsharingcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.carsharingcompany.domain.Car;
import ru.mirea.carsharingcompany.domain.Order;
import ru.mirea.carsharingcompany.domain.OrderForm;
import ru.mirea.carsharingcompany.domain.User;
import ru.mirea.carsharingcompany.repo.CarRepo;
import ru.mirea.carsharingcompany.repo.OrderRepo;
import ru.mirea.carsharingcompany.repo.UserRepo;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    CarRepo carRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", user.getUsername());
            return "index";
        }
        model.addAttribute("user", "anonymous");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping("/foradmin")
    public String forAdmin() {
        return "foradmin";
    }


    @GetMapping("/reserve")
    public String reserve(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            model.addAttribute("user", "anonymous");
        }
        Iterable<Car> cars = carRepo.findAllByReserved(false);
        model.addAttribute("cars", cars);
        return "reserve";
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/reserved")
    public String reserved(@RequestParam String mark, @AuthenticationPrincipal User user, OrderForm form) {
        Car car = carRepo.findFirstByMarkAndReserved(mark, false);
        if (car == null) {
            return "/error";
        }
        car.setReserved(true);
        user.setCarId(car.getId());
        Order order = form.toOrder(user, car);
        carRepo.save(car);
        orderRepo.save(order);
        user.setOrderId(order.getId());
        userRepo.save(user);
        return "redirect:/user";
    }



    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        Iterable<Order> orders = orderRepo.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        model.addAttribute("car", carRepo.findCarById(user.getCarId()));

        return "user";
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/unreserve")
    public String unreserve(@AuthenticationPrincipal User user, Model model) {

        Date date = new Date();

        Order order = orderRepo.findOrderById(user.getOrderId());
        order.setEndDataAndTime(date.toString());
        Car car = carRepo.findCarById(user.getCarId());
        order.setPrice(order.calculPrice(order.getStartDataAndTime(), order.getEndDataAndTime(), car.getCarPrice()));
        orderRepo.save(order);


        car.setReserved(false);
        carRepo.save(car);

        user.setCarId(null);
        user.setOrderId(null);
        userRepo.save(user);


        return "redirect:/user";
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
