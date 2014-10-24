package com.kricko.tvshowupdater.refactor;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRefactorer {

	public void doRefactor(String parentDirectory){
		try {
			List<Path> dirs = getDirectories(Paths.get(parentDirectory));

			for(Path dir:dirs){
				System.out.println(dir.toString());

				List<Path> files = getMovieFiles(dir);
				refactorFiles(files, dir, parentDirectory);
				deleteDirectory(dir);
			}

			List<Path> files = getMovieFiles(Paths.get(parentDirectory));
			refactorFiles(files, Paths.get(parentDirectory), null);

		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	private void refactorFiles(List<Path> files, Path dir, String parentDir) throws IOException{
		Pattern pattern = Pattern.compile("(^|)S([0-9]+)E([0-9]+)");

		boolean isParent = (parentDir == null) ? false : true;
		Matcher itemMatcher = null;
		String episodeName = null;
		if(isParent){
			itemMatcher = pattern.matcher(dir.toString());
			while(itemMatcher.find()){
				episodeName = itemMatcher.group();
			}
		}
		for(Path file:files){
			if(!isParent){
				itemMatcher = pattern.matcher(file.toString());
				while(itemMatcher.find()){
					episodeName = itemMatcher.group();
				}
			}
			String fileName = file.getFileName().toString();
			System.out.println(fileName);
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = episodeName + ext;

			String newDir = (parentDir == null) ? dir.toString() : parentDir;
			Path target = Paths.get(newDir.toString(), newFileName);
			System.out.println("Moving " + file.toString() + " to " + target.toString());

			Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	private List<Path> getDirectories(final Path dir) throws IOException {
		final List<Path> dirlist = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
				Path subDir = dir.resolve(it.next());
				if(Files.isDirectory(subDir)){
					dirlist.add(subDir);
				}
			}
		}
		return dirlist;
	}


	private List<Path> getMovieFiles(final Path dir) throws IOException {
		final List<Path> fileList = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (final Iterator<Path> it = stream.iterator(); it.hasNext();) {
				Path file = dir.resolve(it.next());
				if(!Files.isDirectory(file)){
					String contentType = Files.probeContentType(file);
					if(contentType != null && contentType.startsWith("video")
							&& !file.toString().contains("sample")
							&& !file.toString().contains("Sample")){
						System.out.println(file.toString() + " content Type is "+ contentType);
						fileList.add(file);
					}
				}
			}
		}
		return fileList;
	}

	private void deleteDirectory(Path path) throws IOException{
		Files.walkFileTree(path, new FileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				System.out.println("deleting directory :"+ dir);
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("Deleting file: "+file);
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				System.out.println(exc.toString());
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
