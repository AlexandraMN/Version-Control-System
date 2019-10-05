package vcs;

import filesystem.FileSystemSnapshot;

// Am creat clasa commit pentru a retine toate informatiile despre commit
// -nume
// -id
// -fileSystemSnapshot

public class Commit {
    private String name;
    private int id;
    private FileSystemSnapshot fileSystemSnapshot;

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final FileSystemSnapshot getFileSystemSnapshot() {
        return fileSystemSnapshot;
    }

    public final void setFileSystemSnapshot(FileSystemSnapshot fileSystemSnapshot) {
        this.fileSystemSnapshot = fileSystemSnapshot;
    }
}
