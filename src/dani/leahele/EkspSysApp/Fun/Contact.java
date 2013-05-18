package dani.leahele.EkspSysApp.Fun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact> {

	private static final long serialVersionUID = 5755428505291515709L;

	public final String name;
	public final int imageOnline;
	public final int imageOffline;

	private boolean isOnline;
	private boolean isFavorite;

	public Contact(String name, int imageOnline, int imageOffline) {
		this.name = name;
		this.imageOnline = imageOnline;
		this.imageOffline = imageOffline;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean b) {
		isOnline = b;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite() {
		isFavorite = !isFavorite;
	}

	@Override
	public int compareTo(Contact other) {
		if ((isFavorite && other.isFavorite)
				|| (!isFavorite && !other.isFavorite)) {
			// Both are favorites or not favorites
			if ((isOnline && other.isOnline) || (!isOnline && !other.isOnline)) {
				// Both are online or not online
				return name.compareTo(other.name);
			} else if (isOnline && !other.isOnline) {
				// this is online
				return -1;
			} else if (!isOnline && other.isOnline) {
				// other is online
				return 1;
			}
		}
		if (isFavorite && !other.isFavorite) {
			// this is favorite
			return -1;
		} else {
			// other is favorite
			return 1;
		}
	}
	
	public void save(File fileDir) {

		// Create file
		File file = new File(fileDir, name);

		// Save event in file
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
