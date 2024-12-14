package com.chwltd.tools;


import android.net.Uri;
import java.io.File;

interface PathStrategy {
    File getFileForUri(Uri uri);
    Uri getUriForFile(File file);
}

