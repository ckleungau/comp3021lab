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
		List<Note> searchList = new ArrayList<Note>();
		String[] tokens = keywords.toLowerCase().split(" ", 0);
		boolean matchTitle = false;
		boolean matchContent = false;
		
		
		for (Note note : notes) {
			
			int index = 0;
			if (note instanceof ImageNote) {
				while (index < tokens.length) {
					if (index != tokens.length-1) {
						if (tokens[index+1].equals("or")) {
							if (note.getTitle().toLowerCase().contains(tokens[index]) || note.getTitle().toLowerCase().contains(tokens[index+2])) {
								matchTitle = true;
								index = index+3;
							}else {
								matchTitle = false;
								break;
							}
						}else if (!tokens[index+1].equals("or")) {
							if (note.getTitle().toLowerCase().contains(tokens[index])) {
								matchTitle = true;
								index++;
							}else {
								matchTitle = false;
								break;
							}
						}
					}else {
						if (note.getTitle().toLowerCase().contains(tokens[index])) {
							matchTitle = true;
							index++;
						}else {
							matchTitle = false;
							break;
						}
					}
				}
			}else if (note instanceof TextNote) {
				TextNote tNote = (TextNote)note;
				while(index < tokens.length) {
					if (index != tokens.length-1) {
						if (tokens[index+1].equals("or")) {
							if (tNote.getTitle().toLowerCase().contains(tokens[index]) || tNote.getTitle().toLowerCase().contains(tokens[index+2]) ||
								tNote.getContent().toLowerCase().contains(tokens[index]) || tNote.getContent().toLowerCase().contains(tokens[index+2])) {
								matchContent = true;
								index = index+3;
							}else {
								matchContent = false;
								break;
							}
						}else if (!tokens[index+1].equals("or")) {
							if (tNote.getTitle().toLowerCase().contains(tokens[index]) || tNote.getContent().toLowerCase().contains(tokens[index])) {
								matchContent = true;
								index++;
							}else {
								matchContent = false;
								break;
							}
						}
					}else {
						if (tNote.getTitle().toLowerCase().contains(tokens[index]) || tNote.getContent().toLowerCase().contains(tokens[index])) {
							matchContent = true;
							index++;
						}else {
							matchContent = false;
							break;
						}
					}
				}
			}
			if (matchTitle || matchContent) { searchList.add(note); }
		}
		return searchList;
	}

}
