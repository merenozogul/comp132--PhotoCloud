package photoSharing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Filters.ImageSecretary;
import UsersAdministrators.Users;

public class SharedPhoto {
	Users owner;
	String name;
	String extension;
	int likes;
	int dislikes;
	String comments;
	ArrayList<String> likePersons= new ArrayList<>();
	ArrayList<String> dislikePersons= new ArrayList<>();
	public SharedPhoto(String name,String extension,int likes,int dislikes,Users owner) throws IOException {
		ImageSecretary.writeFilteredImageToResources(ImageSecretary.readResourceImage(name, extension), owner.getNICKNAME()+"_Shared_"+name, extension);
		this.name=owner.getNICKNAME()+"_Shared_"+name;
		this.extension=extension;
		this.owner=owner;
		
		System.out.println("./src/SharedPhotoComments/"+this.name+extension+"_dislikes.txt");
		File commentFile = new File("./src/SharedPhotoComments/"+this.name+extension+"_comments.txt");
        if (commentFile.exists()) {
            try {
                Scanner scanner = new Scanner(commentFile);


                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    this.comments+=line+"\n";
                }

                scanner.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
				FileWriter fileWriter = new FileWriter(commentFile);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File likesFile = new File("./src/SharedPhotoComments/"+this.name+extension+"_likes.txt");
        if (likesFile.exists()) {
            try {
                arrayGetter(likesFile,likePersons);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
				FileWriter fileWriter = new FileWriter(likesFile);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File dislikesFile = new File("./src/SharedPhotoComments/"+this.name+extension+"_dislikes.txt");
        if (dislikesFile.exists()) {
            try {
                arrayGetter(dislikesFile,dislikePersons);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
				FileWriter fileWriter = new FileWriter(dislikesFile);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        this.likes=likePersons.size();
		this.dislikes=dislikePersons.size();
        
	}
	
	
	/**
	 * Add the user in likePersons array list and removes that user from dislikesPersons array list if that user disliked
	 * that photo before.
	 * @param user user that liked the photo
	 */
	public void likeAPhoto(Users user) {
		if (dislikePersons.contains(user.getNICKNAME())) {
			dislikePersons.remove(user.getNICKNAME());
			try {
	            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_dislikes.txt");
	            for (String usr:dislikePersons) {
	            	writer.write(usr+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
			this.setDislikes(dislikePersons.size());
			likePersons.add(user.getNICKNAME());
			try {
	            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_likes.txt");
	            for (String usr:likePersons) {
	            	writer.write(usr+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
			this.setLikes(likePersons.size());
			
		}
		else if (!dislikePersons.contains(user.getNICKNAME()) && !likePersons.contains(user.getNICKNAME())) {
			likePersons.add(user.getNICKNAME());
			try {
	            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_likes.txt");
	            System.out.println(222);
	            System.out.println("./src/SharedPhotoComments/"+name+extension+"_likes.txt");
	            for (String usr:likePersons) {
	            	writer.write(usr+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
			this.setLikes(likePersons.size());
		
		}
		
		
	}
	
	
	/**
	 * Add the user in dislikePersons array list and removes that user from likesPersons array list if that user liked
	 * that photo before.
	 * @param user user that disliked the photo
	 */
	public void dislikeAPhoto(Users user) {
		
		if (likePersons.contains(user.getNICKNAME())) {
			likePersons.remove(user.getNICKNAME());
			try {
	            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_likes.txt");
	            for (String usr:likePersons) {
	            	writer.write(usr+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
			this.setLikes(likePersons.size());
			dislikePersons.add(user.getNICKNAME());
			try {
	            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_dislikes.txt");
	            for (String usr:dislikePersons) {
	            	writer.write(usr+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
			this.setDislikes(dislikePersons.size());
			
		}
		else if (!likePersons.contains(user.getNICKNAME()) && !dislikePersons.contains(user.getNICKNAME())) {
			dislikePersons.add(user.getNICKNAME());
			try {
	            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_dislikes.txt");
	            for (String usr:dislikePersons) {
	            	writer.write(usr+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
			this.setDislikes(dislikePersons.size());
		
		}
		
	}
	
	/**
	 * Takes a like back by removing user from likesPersons
	 * @param user user that took their like back
	 */
	public void notLikeAPhoto(Users user) {
		likePersons.remove(user.getNICKNAME());
		try {
            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_likes.txt");
            for (String usr:likePersons) {
            	writer.write(usr+"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		this.setLikes(likePersons.size());

	
	}
	
	/**
	 * Takes a dislike back by removing user from dislikesPersons
	 * @param user that took their dislike back
	 */
	public void notDislike(Users user) {
		dislikePersons.remove(user.getNICKNAME());
		try {
            FileWriter writer = new FileWriter("./src/SharedPhotoComments/"+name+extension+"_dislikes.txt");
            for (String usr:dislikePersons) {
            	System.out.println(usr);
            	writer.write(usr+"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		this.setDislikes(dislikePersons.size());

	}
	

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public int getDislikes() {
		return dislikes;
	}

	public int getLikes() {
		return likes;
	}
	public Users getOwner() {
		return owner;
	}
	public String getName() {
		return name;
	}
	public String getExtension() {
		return extension;
	}
	public String getComments() {
		return comments;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public ArrayList<String> getLikePersons() {
		return likePersons;
	}
	public void setLikePersons(ArrayList<String> likePersons) {
		this.likePersons = likePersons;
	}
	public ArrayList<String> getDislikePersons() {
		return dislikePersons;
	}
	public void setDislikePersons(ArrayList<String> dislikePersons) {
		this.dislikePersons = dislikePersons;
	}
	
	
	/**
	 * Adds comment to a SharedPhoto object
	 * @param comment A string that consist of the user's nickname who commented and the comment message
	 */
	public void addComment(String comment) {
		this.comments+=comment+"\n";
		File commentFile = new File("./src/SharedPhotoComments/"+name+extension+"_comments.txt");
		 try {
             FileWriter writer = new FileWriter(commentFile, true); 
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             String newLine = comment+"\n";

             bufferedWriter.write(newLine);
             bufferedWriter.close();

         } catch (IOException e) {
             e.printStackTrace();
         }
	}
	
	/**
	 * Takes a fileName, comment file for that SharedPhoto, and adds the comments to to second parameter which is an array
	 * list of strings and stores the comments made by users.
	 * @param fileName A special file that has comments made for a specific SharedPhoto
	 * @param array the array list that comments will be stored
	 * @throws FileNotFoundException if there is no file named fileName it is thrown
	 */
	public void arrayGetter(File fileName,ArrayList<String> array) throws FileNotFoundException {
		Scanner scanner = new Scanner(fileName);


        while (scanner.hasNextLine()) {
            String line3 = scanner.nextLine();
      
        	array.add(line3);

        }

        scanner.close();
	}
	

}
