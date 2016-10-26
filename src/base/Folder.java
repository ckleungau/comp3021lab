package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>,Serializable {

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

    public boolean removeNotes(String title) {
        List<Note> trash= searchNotes(title);
        if (trash != null) {
            notes.removeAll(trash);
            return true;
        }
        return false;
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
		List<Note> searchList = new ArrayList<Note>();
		String[] key_tokens = keywords.toLowerCase().split(" ");
		
		List<String> orList = new ArrayList<>();
		List<String> andList = new ArrayList<>();
		
		// Loop key_tokens to separate key_tokens into sublists
		int count = 0;
		while (count < key_tokens.length) {
			String str = "";
			// add keys to check with or-relationship
			if ((count+1 < key_tokens.length) && (key_tokens[count+1].equals("or"))) {
				while ((count+1 < key_tokens.length) && (key_tokens[count+1].equals("or"))) {
					str+= " " + key_tokens[count];
					count+=2;
				}
				str += " " + key_tokens[count];
				orList.add(str);
				count++;
			}else {
				andList.add(key_tokens[count]);
				count++;
			}
		}
		// add keys to check with and-relationship
		if (orList.size() > 0) {
			andList.addAll(orList);
		}
		
		for (Note note : this.getNotes()) {
			// setup boolean variables for checking whether the notes were matched
			boolean found = false;
			boolean and_check = false;
			if (note instanceof ImageNote) {
				// Search for ImageNote
				String img_title = note.getTitle().toLowerCase();
				for (String str : andList) {
					and_check = true;
					boolean or_check = false;
					String[] or_keys = str.trim().split(" ");
					for (int j=0; j < or_keys.length; j++) {
						if (img_title.contains(or_keys[j])) {
							or_check = true;
						}
					}
					found = (and_check && or_check);
				}
				if (found) {
					searchList.add(note);
				}
			}else if (note instanceof TextNote) {
				// Search for TextNote
				TextNote tNote = (TextNote)note;
				String txt_title = tNote.getTitle().toLowerCase();
				String txt_content = tNote.getContent().toLowerCase();
				for (String str : andList) {
					and_check = true;
					boolean or_check = false;
					String[] or_keys = str.trim().split(" ");
					for (int j=0; j < or_keys.length; j++) {
						if (txt_title.contains(or_keys[j]) || txt_content.contains(or_keys[j])) {
							or_check = true;
						}
					}
					found = (and_check && or_check);
				}
				if (found) {
					searchList.add(note);
				}
			}
		}
				
		return searchList;
	}

}
