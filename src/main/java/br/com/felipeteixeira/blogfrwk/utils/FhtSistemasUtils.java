package br.com.felipeteixeira.blogfrwk.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.felipeteixeira.blogfrwk.payload.response.AlbumResponse;


public class FhtSistemasUtils {
	public final static String TOKEN = "BLOG_EXE_TEST_FHT@";
	
	
	   public static void createFile(String path, String name, byte[] file) {
	        try {
	        	
	            Files.write(Paths.get(path+name), file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	   
	    
		public static void createDir(String path) {
			File dir = new File(path);

			dir.mkdir();
		}
		
		private static String cript(String texto) {
			try {
				texto =  TOKEN+ texto + TOKEN;
				MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
				byte messageDigest[] = algorithm.digest(texto.getBytes("UTF-8"));

				return bytesToHex(messageDigest);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private static String bytesToHex(byte[] hash) {
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		
		
		public static String getUniquecode() {
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
			String formattedDateTime = currentDateTime.format(formatter);
			return cript(formattedDateTime+Math.random());
			
		}
		
		
		public static void getAllFiles(Long idUser, String directoryBase, String directoryName, List<AlbumResponse> files) {
		    File directory = new File(directoryName);
		    
		    AlbumResponse album = new AlbumResponse();

		    // Get all files from a directory.
		    File[] fList = directory.listFiles();
		    if(fList != null)
		        for (File file : fList) {      
		            if (file.isFile()) {
		            	
		            	album.setName(file.getName());
		            	album.setPath(file.getAbsolutePath().replace(directoryBase, ""));
		            	album.setIdUser(idUser);
		            	if(idUser == null) {
		            		album.setIdUser(Long.valueOf(album.getPath().replace("/"+album.getName(), "")));
		            	}
		            
		            	files.add(album);
		            	
		            } else if (file.isDirectory()) {
		            	 getAllFiles(idUser, directoryBase, file.getAbsolutePath(), files);
		            }
		        }
		    }
		
		
		public static void deleteFile(String path){
			File file = new File(path);
			if(file != null && file.exists()) {
				file.delete();	
			}
			
		}

}
