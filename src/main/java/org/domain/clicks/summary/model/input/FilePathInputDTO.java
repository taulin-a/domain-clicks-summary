package org.domain.clicks.summary.model.input;

import com.beust.jcommander.Parameter;

public class FilePathInputDTO {
    @Parameter(names = {"--file", "-f"}, required = true)
    private String pathStr;

    public FilePathInputDTO(String pathStr) {
        this.pathStr = pathStr;
    }

    public FilePathInputDTO() {
    }

    public String getPathStr() {
        return pathStr;
    }

    public void setPathStr(String pathStr) {
        this.pathStr = pathStr;
    }
}
