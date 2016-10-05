package base;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Comparable<NoteBook>, Serializable{

	private static final long serialVersionUID = 1L;
	
	// Lab 2
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	// Task 5.2
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook nb = (NoteBook) in.readObject();
			this.folders = nb.getFolders();
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
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

    // Lab 5
	@Override
	public int compareTo(NoteBook arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean save(String file) {
		// TODO
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			// TODO
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			fos.close();
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
}
