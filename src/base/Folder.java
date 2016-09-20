package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder> {

	// Lab 2
    private ArrayList<Note> notes;
    private String name;

    public Folder(String name) {
        this.name = name;
        notes = new ArrayList<Note>();
    }

    public void addNote(Note newNote) {
        notes.add(newNote);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note note : notes) {
			if (note instanceof TextNote) { nText++; }
			if (note instanceof ImageNote) { nImage++; }
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
    
    // Lab 3
	@Override
	public int compareTo(Folder o) {
		// smaller name is considered as smaller
		if (this.name.compareTo(o.name) > 0) {
			return 1;
		}else if (this.name.compareTo(o.name) < 0) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public void sortNotes() {
		List<Note> collection = new ArrayList<Note>();
		Collections.sort(collection);
	}

	public List<Note> searchNotes(String keywords) {
		System.out.println(keywords);
		
		List<Note> searchList = new ArrayList<Note>();
		for (Note note : notes) {
			String[] tokens = keywords.split(" ", 0);
			for (int i = 0; i < tokens.length; i++) {

				if (tokens[i] == "or" || tokens[i] == "OR" || tokens[i] == "oR" || tokens[i] == "Or"  ) {
//					if (note.equals(tokens[i-1]) || note.equals(tokens[i+1])) {
//						searchList.add(note);
//					}
					System.out.println(tokens[i]);
				}
				System.out.println(tokens[i]);
			}
//				if (note instanceof ImageNote) {
//					if (note.getTitle().equals(tokens[i])) {
//						searchList.add(note);
//					}
//				}
//				if (note instanceof TextNote) {
//					if (note.getTitle().equals(tokens[i]) || note.get)
//				}
//				
//				
//				
//				if (tokens[i] == "or" || tokens[i] == "OR" || tokens[i] == "oR" || tokens[i] == "Or"  ) {
//					if (note.equals(tokens[i-1]) || note.equals(tokens[i+1])) {
//						searchList.add(note);
//					}
//				}
//			}
		}
		return searchList;
	}

}
