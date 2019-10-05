package vcs;

import java.util.List;

// Am creat clasa Branch pentru a putea retine toate informatiile despre Branch:
// -numele
// -lista de commituri

public class Branch {
    private List<Commit> branch;
    private String name;

    public final List<Commit> getBranch() {
        return branch;
    }

    public final void setBranch(List<Commit> branch) {
        this.branch = branch;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

}
