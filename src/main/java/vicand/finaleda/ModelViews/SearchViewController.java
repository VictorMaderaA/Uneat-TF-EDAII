package vicand.finaleda.ModelViews;

import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import vicand.finaleda.controllers.AppController;
import vicand.finaleda.models.FileSearchData;

public class SearchViewController {

	public ArrayList<String[]> Search(String searchWords) {
		ArrayList<ArrayList<FileSearchData>> data = AppController.Search(searchWords);

		ArrayList<FileSearchData> last = new ArrayList<FileSearchData>();

		for (ArrayList<FileSearchData> d : data) {
			last.addAll(d);
		}

		Collections.sort(last);

		ArrayList<String[]> resultArray = new ArrayList<String[]>();
		for (FileSearchData searchData : last) {
			String path = searchData.getFilePath();
			File f = new File(path);

			path = f.getAbsolutePath();
			String fileName = f.getName();
			String extension = getFileExtension(f);

			String[] fileResult = new String[] { fileName, path, extension };
			resultArray.add(fileResult);
		}

		return resultArray;
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

}
