package org.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.application.Repository.DocumentRepo;
import org.application.entity.Documents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {
    
    @Autowired
    private DocumentRepo docRepo;

    @PostMapping("/saveDocument")
	public Documents saveEMployee(@RequestBody Documents doc, HttpServletRequest request){
		
		return docRepo.save(doc);
	}
    @GetMapping("/allDocuments")
    public List<Documents> getAllDocuments(){
        return docRepo.findAll();
    }

    @DeleteMapping("/document/{docId}")
	public String deleteEmployee(@PathVariable("docId") Integer docId) {
		docRepo.deleteById(docId);
		return "Document Deleted";
	}
    @GetMapping("/document/Read/{docId}")
	public Documents getDocumentToRead(@PathVariable("docId") Integer docId) {
		return docRepo.findById(docId).get();
	}
    @GetMapping("/document/Download/{docId}")
	public Documents getDocumentForDownload(@PathVariable("docId") Integer docId) {
		return docRepo.findById(docId).get();
	}

}
