package com.example.test_plugin.strategy.behavioral;

import com.example.test_plugin.MyAction;
import com.example.test_plugin.dialog.behavioral.CommandDialog;
import com.example.test_plugin.strategy.DialogHelper;
import com.example.test_plugin.strategy.GenerateCodeStrategy;
import com.intellij.openapi.ui.DialogWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class CommandStrategy extends DialogHelper {

    private CommandStrategy(String basePackagePath) {
        super(basePackagePath);
    }

    @Override
    protected Map<String, String> generateCodeForClasses() {
        try {
            CommandDialog dialog = new CommandDialog(MyAction.project);
            if (!showDialog(dialog))return null;
            //java 文件map
            map = new HashMap<>();
            //从dialog中获取数据
            String abstractCommand = dialog.getAbstractCommand();
            Map<String, String> concreteCommandAndReceiver = dialog.getConcreteCommandAndReceiver();
            String commandInvoker = dialog.getCommandInvoker();
            //生成 data Map
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("abstractCommand", abstractCommand);
            //生成抽象命令
            Template abstractCommandTemplate = cfg.getTemplate("AbstractCommand.ftl");
            Writer abstractCommandOut = new StringWriter();
            abstractCommandTemplate.process(dataMap, abstractCommandOut);
            map.put(abstractCommand, abstractCommandOut.toString());
            //生成具体命令
            Template concreteCommandTemplate = cfg.getTemplate("ConcreteCommand.ftl");
            for (String concreteCommand : concreteCommandAndReceiver.keySet()) {
                String receiver = concreteCommandAndReceiver.get(concreteCommand);
                dataMap.put("concreteCommand", concreteCommand);
                dataMap.put("receiver", receiver);
                Writer concreteCommandOut = new StringWriter();
                concreteCommandTemplate.process(dataMap, concreteCommandOut);
                map.put(concreteCommand, concreteCommandOut.toString());
            }
            //生成接收者
            Template receiverTemplate = cfg.getTemplate("Receiver.ftl");
            for (String receiver : concreteCommandAndReceiver.values()) {
                dataMap.put("receiver", receiver);
                Writer receiverOut = new StringWriter();
                receiverTemplate.process(dataMap, receiverOut);
                map.put(receiver, receiverOut.toString());
            }
            //生成调用者
            Template invokerTemplate = cfg.getTemplate("Invoker.ftl");
            dataMap.put("invoker", commandInvoker);
            Writer invokerOut = new StringWriter();
            invokerTemplate.process(dataMap, invokerOut);
            map.put(commandInvoker, invokerOut.toString());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    private static class SingletonHolder {
        private static final CommandStrategy INSTANCE = new CommandStrategy("command");
    }

    //对外提供静态方法获取该对象
    public static CommandStrategy getInstance() {
        return CommandStrategy.SingletonHolder.INSTANCE;
    }
}
