package com.bridgelabz.fundoonotes.controller;

/*
 *  author : Lavanya Manduri
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.model.ProfilePic;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.ProfilePicService;

@RestController
public class ProfilePicController {

	@Autowired
	private ProfilePicService profileService;
	
	/* API for Uploading the Picture */
	
	@PostMapping("/upload-ProfilePic/{token}")
	public ResponseEntity<Responses> uploadPic(@ModelAttribute MultipartFile multipartFile,
			@RequestParam String fileName, @PathVariable("token") String token) {
		ProfilePic result = profileService.uploadProfilePic(multipartFile, fileName, token);
		
			return ResponseEntity.status(HttpStatus.OK).body(new Responses("successfully uploaded", result));
		
	}

	/* API for Deleting the Picture */

	@DeleteMapping("/delete-profilePic")
	public ResponseEntity<Responses> deletePicture(@RequestParam Long profileId) {
		boolean result = profileService.deleteFileName(profileId);
		
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Responses("successfully profile pic deleted", result));
	}
	
}