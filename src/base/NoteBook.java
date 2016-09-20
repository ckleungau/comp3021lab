package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {

	// Lab 2
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
	
	// Lab 3
	public void sortFolders() {
		for(Folder f : folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> searchResult = new ArrayList<Note>();
		for(Folder f : folders) {
			searchResult.addAll(f.searchNotes(keywords));
		}
		return searchResult;
	}
	
	
    public boolean createTextNote(String folderName, String title, String content) {
    	TextNote note = new TextNote(title, content);
    	return insertNote(folderName, note);
    }
	
}
