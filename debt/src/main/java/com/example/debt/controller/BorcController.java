package com.example.debt.controller;

import com.example.debt.dto.BorcDto;
import com.example.debt.entity.Borc;
import com.example.debt.service.BorcService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/borclar")
@AllArgsConstructor
public class BorcController {

    private BorcService borcService;

    @PostMapping
    public ResponseEntity save(@RequestBody BorcDto borcDto){
        return borcService.save(borcDto);

    }
    @GetMapping("date/{startDate}/{endDate}")
    public List<BorcDto> findByOluşmaTarihiGreaterThanEqualAndOluşmaTarihiLessThanEqual(@PathVariable String startDate,@PathVariable String endDate) {
        return borcService.findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate.parse(startDate),LocalDate.parse(endDate));
    }


    @GetMapping
    public List<BorcDto> findByAll(){
        return borcService.findByAll();
    }
    @GetMapping("/user/{kullaniciId}")
    public List<BorcDto> findAllBorcByKullaniciId(@PathVariable Long kullaniciId){
        return borcService.findAllBorcByKullaniciId(kullaniciId);
    }
    @GetMapping("/user/anaBorc/{kullaniciId}")
    public List<BorcDto> findAllAnaBorcByKullanaciId(@PathVariable Long kullaniciId){
        return borcService.findAnaBorcByKullaniciId(kullaniciId);
    }
    @GetMapping("/user/vadeBorcList/{kullaniciId}")
    public List<BorcDto> findAllVadeBorcByKullanaciId(@PathVariable Long kullaniciId){
        return borcService.findAllVadeBorcByKullanaciId(kullaniciId);
    }
    @GetMapping("/user/vadeBorc/{kullaniciId}")
    public BorcDto findByKullaniciIdOrderByKalanBorcTutari(@PathVariable Long kullaniciId){
        return borcService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
    }
    @GetMapping("/user/vadeBorcAnlık/{kullaniciId}")
    public BorcDto findByKullaniciIdOrderByKalanBorcTutarii(@PathVariable Long kullaniciId){
        return borcService.findByKullaniciIdOrderByKalanBorcTutarii(kullaniciId);
    }

}
