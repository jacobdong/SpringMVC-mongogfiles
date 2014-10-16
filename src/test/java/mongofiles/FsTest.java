package mongofiles;

import java.io.File;
import java.io.IOException;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.xcode.tool.ToolMongoDb;

public class FsTest {

    public static void main(String[] args) throws IOException {
        ToolMongoDb.initPool();
        GridFS fs = ToolMongoDb.getGridfs();

        File file = new File("D:/readMe.txt");
        GridFSInputFile gfs = fs.createFile(file);

        gfs.put("auth", "jacobdong");
        gfs.save();

        System.out.println("save success");

    }

}
