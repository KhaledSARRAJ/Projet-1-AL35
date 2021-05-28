package fr.eql.projet01.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Support;
import fr.eql.projet01.service.PublicationService;
import fr.eql.projet01.service.SupportService;

@Controller
@RequestMapping("/admin/product")
public class PublicationManagementController {

	private PublicationService productService;

	@Autowired
	public void setProductService(PublicationService productService) {
		this.productService = productService;
	}

	@Autowired
	private SupportService supportservice;

	@GetMapping(path = "/s")
	public String publications(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		Page<Publication> publicationsList = productService.findByTitreIgnoreCaseContains(motCle,
				PageRequest.of(page, size));
		model.addAttribute("publicationsList", publicationsList);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", motCle);
		model.addAttribute("pages", new int[publicationsList.getTotalPages()]);

		return "publicationManagement";
	}

	@GetMapping("/find/{id}")
	public String getProductById(@PathVariable("id") Long id, Model model) {
		System.out.println("ID: " + id);
		Publication optionalProductDto = productService.findById(id);

		List<Support> listSupport = supportservice.findByPublicationSupport(optionalProductDto);
		optionalProductDto.setSupport(listSupport);

		model.addAttribute("productDto", optionalProductDto);
		return "adminProductDetails";
	}

	@GetMapping(path = "/deletePublications")
	public String delete(long id, String motCle, String page, String size, RedirectAttributes redirectAttributes) {
		productService.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "Delete Product ID: " + id + " is Done");
		redirectAttributes.addFlashAttribute("alertClass", "alert-info");
		return "redirect:/admin/product/s?page=" + page + "&motCle=" + motCle + "&size=" + size;
	}

	@GetMapping(value = "/FormPublication")
	public String formPublication(Model model) {
		model.addAttribute("publication", new Publication());
		return "FormPublication";
	}

	@PostMapping(value = "/save")
	public String save(Model model, @Valid Publication publication, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "publicationAddForm";
		}
		productService.saveOrUpdate(publication);
		return "adminProductDetails";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") Long id) {
		Publication p = productService.findById(id);
		// .orelse(null)
		List<Support> listSupport = supportservice.findByPublicationSupport(p);
		p.setSupport(listSupport);

		model.addAttribute("productDto", p);
		return "EditPublication";
	}

    @GetMapping("/uploadImageForm/{id}")
    public String showImageForm(@PathVariable("id") Long id,Model model){
        //productService.findById(id);
        model.addAttribute("id",id);
        return "publicationAddImageFrom";    }

    @PostMapping("/upload/{id}")
    public String uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,Model model, RedirectAttributes redirectAttributes){
        System.out.println("id = " + id);
        System.out.println("file.getSize() = "+file.getSize());
        System.out.println("Image Size KB:" + file.getSize() / 1024);
	if (file.getSize() == 0){
            redirectAttributes.addFlashAttribute("message","Select Image file (jpg/png)");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
            return "redirect:/admin/product/uploadImageForm/"+id;
        }

        if (file.getSize()/1024 >= 300){
           redirectAttributes.addFlashAttribute("message","Image size is not valid ( max size should be less than 300 KB )");
           redirectAttributes.addFlashAttribute("alertClass","alert-danger");
	return "redirect:/admin/product/uploadImageForm/"+id;
        }

       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png")) ){
            redirectAttributes.addFlashAttribute("message","Image type is not valid");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
            return "redirect:/admin/product/uploadImageForm/"+id;
        }
        Publication dto= new Publication();
        Path path= Paths.get("src/main/resources/static/"+fileName);
        try {
        	
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            dto = productService.findById(id);
          //  dto.getSupport().setImage(file.getBytes());
            List<Support> listSupport = supportservice.findByPublicationSupport(dto);
            dto.setSupport(listSupport);

    		model.addAttribute("productDto", dto);
            productService.saveOrUpdate(dto);
            redirectAttributes.addFlashAttribute("message","Image successfully uploaded. file name : " + fileName);
            redirectAttributes.addFlashAttribute("alertClass","alert-success");
        } catch (IOException e) {
            e.printStackTrace();
        }

       return "redirect:/admin/product/s";
   }
}
