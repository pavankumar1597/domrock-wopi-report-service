package ai.domrock.domrockwopireportservice.service.impl;

import ai.domrock.domrockwopireportservice.service.FileUploaderService;
import com.microsoft.azure.storage.blob.BlockBlobURL;
import com.microsoft.azure.storage.blob.ContainerURL;
import com.microsoft.azure.storage.blob.TransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;

@Service
public class FileUploaderServiceImpl implements FileUploaderService {

    private final ContainerURL containerURL;

    @Autowired
    public FileUploaderServiceImpl(ContainerURL containerURL) {
        this.containerURL = containerURL;
    }


    @Override
    public void uploadFileToAzure(File sourceFile) throws IOException {

        BlockBlobURL blockBlobURL = containerURL.createBlockBlobURL(sourceFile.getName());
        final AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(sourceFile.toPath());

        TransferManager.uploadFileToBlockBlob(fileChannel, blockBlobURL, 8 * 1024 * 1024, null)
                .toCompletable()
                .doOnComplete(() -> logInfo("File %s is uploaded.", sourceFile.toPath()))
                .doOnError(error -> logError("Failed to upload file %s with error %s.", sourceFile.toPath(),
                        error.getMessage()))
                .blockingAwait();
    }

    private static void logInfo(String log, Object... params) {
        System.out.println(String.format(log, params));
    }

    private static void logError(String log, Object... params) {
        System.err.println(String.format(log, params));
    }
}
