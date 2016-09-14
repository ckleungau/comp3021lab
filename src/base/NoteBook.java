package base;

import java.util.ArrayList;

public class NoteBook {

	private ArrayList<Folder> folders;
	
	public  NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	private boolean insertNote(String folderName, Note note) {
		// Step 1
		Folder target = null;
		for(Folder f: folders) {
			if (f.getName().equals(folderName)) {
				target = f;
			}
		}
		if (target == null) {
			target = new Folder(folderName);
			folders.add(target);
		}
		
		// Step 2
		for(Note n: target.getNotes()) {
			if (n.getTitle().equals(note.getTitle())) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		target.addNote(note);
		return true;
	}
	
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	
	
	
}
