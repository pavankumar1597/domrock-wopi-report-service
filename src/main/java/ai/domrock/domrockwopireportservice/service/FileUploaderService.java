package ai.domrock.domrockwopireportservice.service;

import com.microsoft.azure.storage.blob.BlockBlobURL;

import java.io.File;
import java.io.IOException;

public interface FileUploaderService {

    void uploadFileToAzure(File sourceFile) throws IOException;
}
