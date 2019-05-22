package vicand.finaleda.models;

import java.io.File;

public class FileSimple {

	private String name;
	private String path;
	private String extension;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}




	public FileSimple(String name, String path, String extension) {
		super();
		this.name = name;
		this.path = path;
		this.extension = extension;
	}

	public FileSimple(File file) {
		name = file.getName();
		path = file.getAbsolutePath();
		extension = getFileExtension(file);
	}





	private static String getFileExtension(File file) {
		String extension = "";

		try {
			if (file != null && file.exists()) {
				String name = file.getName();
				extension = name.substring(name.lastIndexOf("."));
			}
		} catch (Exception e) {
			extension = "";
		}
		return extension;
	}

	@Override
	public String toString() {
		return "File[" + name + ";" + path + ";" + extension + "]";
	}
}
