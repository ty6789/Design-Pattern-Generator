package com.example.test_plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;

public abstract class AbstractPackageNameAction extends AnAction {

    public VirtualFile file;
    public static Project project;
    public String path;
    public static String packageName = "";

    public AbstractPackageNameAction(Icon icon){
        super(icon);
    }

    protected void applyPackageName(AnActionEvent e) {
        path = getPath(e);
        if (path == null)return;
        project = e.getProject();
        String srcPath = getSrcPath(project);
        if (srcPath == null) {
            packageName = "";
        } else if (srcPath.length() == path.length()) {
            packageName = "";
        } else {
            packageName = path.substring(srcPath.length() + 1);
        }
    }

    private String getPath(AnActionEvent e) {
        // 通过 AnActionEvent 获取到 VirtualFile 对象，它代表了当前选中的文件或目录
        file = e.getData(CommonDataKeys.VIRTUAL_FILE);

        // 如果选中的是目录
        if (file != null && file.isDirectory()) {
            // 获取目录的路径
            String directoryPath = file.getPath();
            return directoryPath;
        }
        return null;
    }

    private String getSrcPath(Project project) {
        ProjectRootManager projectRootManager = ProjectRootManager.getInstance(project);
        VirtualFile root = projectRootManager.getFileIndex().getSourceRootForFile(file);
        if (root == null || "".equals(root)){
            return null;
        }
        return root.getPath();
    }
}
