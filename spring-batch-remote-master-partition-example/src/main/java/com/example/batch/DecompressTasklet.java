package com.example.batch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class DecompressTasklet implements Tasklet {
	private Resource inputResource;
	private String targetDirectory;
	private String targetFile;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(inputResource.getInputStream()));
		File targetDirectoryAsFile = new File(targetDirectory);
		if (!targetDirectoryAsFile.exists()) {
			FileUtils.forceMkdir(targetDirectoryAsFile);
		}
		
		BufferedOutputStream dest = null;
		ZipEntry zipEntry = null;
		while ((zipEntry = zis.getNextEntry()) != null) {
			File target = new File(targetDirectory, zipEntry.getName());
			if (!target.exists()) {
				target.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(target);
			dest = new BufferedOutputStream(fos);
			IOUtils.copy(zis, dest);
			
			dest.flush();
			dest.close();
			if (!target.exists()) {
				throw new IllegalStateException("Could not decompress anything from the archive!");
			}
		}
		zis.close();
		return RepeatStatus.FINISHED;
	}

	public void setInputResource(Resource inputResource) {
		this.inputResource = inputResource;
	}

	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
	}
}