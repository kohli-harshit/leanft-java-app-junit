<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>SwingAppModel</className>
    <objects>
        <object type="Window" technology="Java">
            <name>MainWindow</name>
            <codeName>MainWindow</codeName>
            <identification>
                <properties>
                    <property name="Title" type="string" isRegExp="false">SwingSet2</property>
                </properties>
                <index>0</index>
            </identification>
            <children>
                <object type="InternalFrame" technology="Java">
                    <name>Internal Frame Generator</name>
                    <codeName>InternalFrameGeneratorInternalFrame</codeName>
                    <identification>
                        <properties>
                            <property name="Title" type="string" isRegExp="false">Internal Frame Generator</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="CheckBox" technology="Java">
                            <name>Maximizable</name>
                            <codeName>Maximizable</codeName>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="false">Maximizable</property>
                                </properties>
                            </identification>
                        </object>
                        <object type="CheckBox" technology="Java">
                            <name>Above Maximizable</name>
                            <codeName>AboveMaximizable</codeName>
                            <identification>
                                <properties>
                                    <property name="AttachedText" type="string" isRegExp="true">.*</property>
                                </properties>
                                <vri>
                                    <relatedObject path="MainWindow.InternalFrameGeneratorInternalFrame.Maximizable">
                                        <relations>
                                            <relation type="Below" inline="true"/>
                                        </relations>
                                    </relatedObject>
                                </vri>
                            </identification>
                            <tags>
                                <tag>vri</tag>
                            </tags>
                        </object>
                    </children>
                </object>
                <object type="ToolBar" technology="Java">
                    <name>Toolbar</name>
                    <codeName>ToolBar</codeName>
                    <identification>
                        <properties>
                            <property name="TagName" type="string" isRegExp="false">SwingSet2$ToggleButtonToolBar</property>
                        </properties>
                    </identification>
                </object>
                <object type="Editor" technology="Java">
                    <name>JEditorPane</name>
                    <codeName>JEditor</codeName>
                    <identification>
                        <properties>
                            <property name="TagName" type="string" isRegExp="false">JEditorPane</property>
                            <property name="IsVisible" type="boolean" isRegExp="false">true</property>
                            <property name="NativeClass" type="string" isRegExp="false">javax.swing.JEditorPane</property>
                        </properties>
                        <index>0</index>
                    </identification>
                </object>
                <object type="TabControl" technology="Java">
                    <name>ButtonsTab</name>
                    <codeName>ButtonsTabTabControl</codeName>
                    <identification>
                        <properties>
                            <property name="NativeClass" type="string" isRegExp="false">javax.swing.JTabbedPane</property>
                        </properties>
                        <index>1</index>
                    </identification>
                </object>
                <object type="UiObject" technology="Java">
                    <name>ImageButtonsPanel</name>
                    <codeName>ImageButtonsPanelUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="AttachedText" type="string" isRegExp="false">Image Buttons</property>
                            <property name="NativeClass" type="string" isRegExp="false">javax.swing.JPanel</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>generic</tag>
                    </tags>
                </object>
                <object type="CheckBox" technology="Java">
                    <name>Enabled</name>
                    <codeName>EnabledCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="AttachedText" type="string" isRegExp="false">Enabled</property>
                        </properties>
                    </identification>
                </object>
                <object type="RadioButton" technology="Java">
                    <name>Pad_10</name>
                    <codeName>Pad10RadioButton</codeName>
                    <identification>
                        <properties>
                            <property name="AttachedText" type="string" isRegExp="false">10</property>
                            <property name="NativeClass" type="string" isRegExp="false">javax.swing.JRadioButton</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>radio</tag>
                    </tags>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
