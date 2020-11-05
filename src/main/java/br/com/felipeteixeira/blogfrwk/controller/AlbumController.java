package br.com.felipeteixeira.blogfrwk.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipeteixeira.blogfrwk.payload.response.AlbumResponse;
import br.com.felipeteixeira.blogfrwk.payload.response.MessageResponse;
import br.com.felipeteixeira.blogfrwk.security.service.UserDetailsImpl;
import br.com.felipeteixeira.blogfrwk.security.service.UserDetailsServiceImpl;
import br.com.felipeteixeira.blogfrwk.utils.FhtSistemasUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AlbumController {

	/*
	 * @Autowired private ServletContext servletContext;
	 */
	
	/*@Autowired
	UserDetailsServiceImpl userService;*/

	private final String PATH_BLOGFRWK_CLIENT = "/home/felipehts/git/blogfrwk-client/src/assets/album/";

	@PostMapping("/album")
	public String uploadFile(@RequestParam("file") MultipartFile fileUp) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String path = PATH_BLOGFRWK_CLIENT + userDetails.getId() + "/";

			FhtSistemasUtils.createDir(path);

			String fileName = FhtSistemasUtils.getUniquecode();

			FhtSistemasUtils.createFile(path, fileName, fileUp.getBytes());

			return fileName;

		}
		return null;
	}

	@DeleteMapping("/album/{name}")
	public ResponseEntity<?> deleteFile(@PathVariable(value = "name") String fileName) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String path = PATH_BLOGFRWK_CLIENT + userDetails.getId() + "/" + fileName;

			FhtSistemasUtils.deleteFile(path);

			return ResponseEntity.ok(new MessageResponse("File deleted successfully!"));

		}
		return null;
	}

	@GetMapping("/albums")
	public ArrayList<AlbumResponse> getAllFiles() {
		ArrayList<AlbumResponse> files = new ArrayList<AlbumResponse>();
		FhtSistemasUtils.getAllFiles(null, PATH_BLOGFRWK_CLIENT, PATH_BLOGFRWK_CLIENT, files);
		return files;
	}

	@GetMapping("/albums/user")
	public ArrayList<AlbumResponse> getAllFilesByUser() {
		ArrayList<AlbumResponse> files = new ArrayList<AlbumResponse>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String path = PATH_BLOGFRWK_CLIENT + userDetails.getId() + "/";

			FhtSistemasUtils.getAllFiles(userDetails.getId(), path, path, files);
		}

		return files;
	}
	
	
	



}
