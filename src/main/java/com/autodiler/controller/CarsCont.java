package com.autodiler.controller;

import com.autodiler.controller.main.Attributes;
import com.autodiler.model.*;
import com.autodiler.model.enums.BodyType;
import com.autodiler.model.enums.CarStatus;
import com.autodiler.model.enums.Fuel;
import com.autodiler.model.enums.Transmission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/cars")
public class CarsCont extends Attributes {

    @GetMapping("/{id}")
    public String Car(Model model, @PathVariable Long id) {
        AddAttributesCar(model, id);
        return "car";
    }

    @PostMapping("/{id}/comment/add")
    public String CommentAdd(@PathVariable Long id, @RequestParam String text) {
        System.out.println(1);
        Cars car = carsRepo.getReferenceById(id);
        System.out.println(2);
        car.addComment(new Comments(getUser().getUsername(), DateNow(), text));
        System.out.println(3);
        carsRepo.save(car);
        System.out.println(4);
        return "redirect:/cars/{id}";
    }

    @GetMapping("/add")
    public String CarAdd(Model model) {
        AddAttributesCarAdd(model);
        return "carAdd";
    }

    @GetMapping("/conf/{carId}")
    public String CarConf(Model model, @PathVariable Long carId) {
        Cars car = carsRepo.getReferenceById(carId);
        car.setStatus(CarStatus.ACTIVE);
        carsRepo.save(car);
        AddAttributesCarAdd(model);
        return "redirect:/cars/{carId}";
    }

    @GetMapping("/refused/{carId}")
    public String CarRefused(Model model, @PathVariable Long carId) {
        Cars car = carsRepo.getReferenceById(carId);
        car.setStatus(CarStatus.REFUSED);
        carsRepo.save(car);
        AddAttributesCarAdd(model);
        return "redirect:/cars/{carId}";
    }

    @GetMapping("/waiting/{carId}")
    public String CarWaiting(Model model, @PathVariable Long carId) {
        Cars car = carsRepo.getReferenceById(carId);
        car.setStatus(CarStatus.WAITING);
        carsRepo.save(car);
        AddAttributesCarAdd(model);
        return "redirect:/cars/{carId}";
    }

    @GetMapping("/sold/{carId}")
    public String CarSold(Model model, @PathVariable Long carId) {
        Cars car = carsRepo.getReferenceById(carId);
        car.setStatus(CarStatus.SOLD);
        carsRepo.save(car);
        AddAttributesCarAdd(model);
        return "redirect:/cars/{carId}";
    }
    @GetMapping("/on_registration/{carId}")
    public String CarOn_registration(Model model, @PathVariable Long carId) {
        Cars car = carsRepo.getReferenceById(carId);
        car.setStatus(CarStatus.ON_REGISTRATION);
        carsRepo.save(car);
        AddAttributesCarAdd(model);
        return "redirect:/cars/{carId}";
    }

    @GetMapping("/apps")
    public String CarApps(Model model) {
        AddAttributesCarApps(model);
        return "apps";
    }

    @GetMapping("/my")
    public String CarMy(Model model) {
        AddAttributesCarMy(model);
        return "my";
    }

    @GetMapping("/edit/{id}")
    public String CarEdit(Model model, @PathVariable Long id) {
        AddAttributesCarEdit(model, id);
        return "carEdit";
    }

    @GetMapping("/delete/{id}")
    public String CarDelete(@PathVariable Long id) {
        carsRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String CarAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam int year, @RequestParam BodyType bodyType, @RequestParam String description, @RequestParam Fuel fuel, @RequestParam Transmission transmission, @RequestParam int mileage, @RequestParam String tel, @RequestParam String origin, @RequestParam String citySale, @RequestParam String brand) {
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String[] result_photos;
                String result_screenshot;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_screenshot = "cars/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_screenshot));
                    result_photos[i] = result_screenshot;
                }
                Cars car = new Cars(name, price, tel, result_photos);
                car.setDescription(new CarDescription(bodyType, fuel, transmission, description, year, mileage, origin, citySale, brand));
                Users user = getUser();
                user.addCar(car);
                usersRepo.save(user);
            } catch (Exception e) {
                AddAttributesCarAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "carAdd";
            }
        } else {
            AddAttributesCarAdd(model);
            model.addAttribute("message", "Ошибка, некорректный данные!");
            return "carAdd";
        }
        return "redirect:/cars/add";
    }

    @PostMapping("/edit/{id}")
    public String CarEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam int year, @RequestParam BodyType bodyType, @RequestParam String description, @RequestParam Fuel fuel, @RequestParam Transmission transmission, @RequestParam int mileage, @RequestParam String tel, @RequestParam String origin, @RequestParam String citySale, @RequestParam String brand, @PathVariable Long id) {
        Cars car = carsRepo.getReferenceById(id);
        String[] result_photos;
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String result_photo;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_photo = "cars/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_photo));
                    result_photos[i] = result_photo;
                }
                car.setPhotos(result_photos);
            } catch (Exception e) {
                AddAttributesCarAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "carEdit";
            }
        }

        car.setName(name);

        SaleRequest info = car.getSale();

        info.setPrice(price);
        info.setTel(tel);

        CarDescription carDescription = car.getDescription();
        carDescription.setDescription(description);
        carDescription.setYear(year);
        carDescription.setFuel(fuel);
        carDescription.setBodyType(bodyType);
        carDescription.setTransmission(transmission);
        carDescription.setMileage(mileage);
        carDescription.setOrigin(origin);
        carDescription.setCitySale(citySale);
        carDescription.setBrand(brand);

        carsRepo.save(car);

        return "redirect:/";
    }
}
