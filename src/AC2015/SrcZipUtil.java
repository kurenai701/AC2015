package AC2015;
/*
 * Code to Zip src Sources
 * http://stackoverflow.com/questions/15968883/how-to-zip-a-folder-itself-using-java
 * with slight alterations
 * */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SrcZipUtil
{

	private List<String> fileList;

	public SrcZipUtil()
	{
		fileList = new ArrayList<String>();
	}

	public static void main(String[] args)
	{
		SrcZipUtil appZip = new SrcZipUtil();
		appZip.generateFileList(new File(CommonStatic.SOURCE_FOLDER));
		appZip.zipIt(CommonStatic.OUTPUT_ZIP_FILE);
	}

	public void zipIt(String zipFile)
	{
		byte[] buffer = new byte[1024];
		String source = "";
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try
		{
			try
			{
				source = CommonStatic.SOURCE_FOLDER.substring(CommonStatic.SOURCE_FOLDER.lastIndexOf("\\") + 1, CommonStatic.SOURCE_FOLDER.length());
			}
			catch (Exception e)
			{
				source = CommonStatic.SOURCE_FOLDER;
			}
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);
			FileInputStream in = null;

			for (String file : this.fileList)
			{
				System.out.println(".Java File Added : " + file);
				if (!file.endsWith(".java"))
				{
					System.out.println("NOT Added : " + file);	
					continue;
				}        


				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try
				{
					in = new FileInputStream(CommonStatic.SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0)
					{
						zos.write(buffer, 0, len);
					}
				}
				finally
				{
					in.close();
				}
			}

			zos.closeEntry();
			System.out.println("Folder successfully compressed : " + zipFile);

		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				zos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void generateFileList(File node)
	{

		// add file only
		if (node.isFile())
		{
			fileList.add(generateZipEntry(node.toString()));

		}

		if (node.isDirectory())
		{
			String[] subNote = node.list();
			for (String filename : subNote)
			{
				generateFileList(new File(node, filename));
			}
		}
	}

	private String generateZipEntry(String file)
	{
		return file.substring(CommonStatic.SOURCE_FOLDER.length() + 1, file.length());
	}
}    