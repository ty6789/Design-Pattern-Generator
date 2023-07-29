package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.dialog.behavioral.MementoDialog;
import com.example.test_plugin.strategy.DialogHelper;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class MementoStrategy extends DialogHelper {

    private MementoStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        MementoDialog dialog = new MementoDialog();
        dialog.pack();
        dialog.show();
        if (dialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return null;
        String selectedPattern = dialog.getSelectedPattern();
        if ("White-Box Memento".equals(selectedPattern)) {
            return whiteMap();
        }else {
            return blackMap();
        }
    }

    private Map<String, String> blackMap() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成备忘录接口
            Template MementoIFTemplate = cfg.getTemplate("MementoIF.ftl");
            Writer MementoIFOut = new StringWriter();
            MementoIFTemplate.process(dataMap, MementoIFOut);
            map.put("MementoIF", MementoIFOut.toString());
            //生成发起人
            Template BOriginatorTemplate = cfg.getTemplate("BOriginator.ftl");
            Writer BOriginatorOut = new StringWriter();
            BOriginatorTemplate.process(dataMap, BOriginatorOut);
            map.put("Originator", BOriginatorOut.toString());
            //生成管理者
            Template BCaretakerTemplate = cfg.getTemplate("BCaretaker.ftl");
            Writer BCaretakerOut = new StringWriter();
            BCaretakerTemplate.process(dataMap, BCaretakerOut);
            map.put("Caretaker", BCaretakerOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private Map<String, String> whiteMap() {
        try {
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            Map<String, String> dataMap = new HashMap<>();
            //生成发起人
            Template WOriginatorTemplate = cfg.getTemplate("WOriginator.ftl");
            Writer WOriginatorOut = new StringWriter();
            WOriginatorTemplate.process(dataMap, WOriginatorOut);
            map.put("Originator", WOriginatorOut.toString());
            //生成备忘录
            Template WMementoTemplate = cfg.getTemplate("WMemento.ftl");
            Writer WMementoOut = new StringWriter();
            WMementoTemplate.process(dataMap, WMementoOut);
            map.put("Memento", WMementoOut.toString());
            //生成管理者
            Template WCaretakerTemplate = cfg.getTemplate("WCaretaker.ftl");
            Writer WCaretakerOut = new StringWriter();
            WCaretakerTemplate.process(dataMap, WCaretakerOut);
            map.put("Caretaker", WCaretakerOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final MementoStrategy INSTANCE = new MementoStrategy("memento");
    }

    //对外提供静态方法获取该对象
    public static MementoStrategy getInstance() {
        return MementoStrategy.SingletonHolder.INSTANCE;
    }
}
