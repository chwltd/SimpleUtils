package com.chwltd.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * iAppV3项目混淆 2023.12.29 开源！
 * */
public class V3SrcConfused {

    public boolean isTest = false;
    public int _NamespacesSize = 0;
    private String javadir = null;
    public V3SrcConfused()
    {
        // 各种语言混合的字符串
        this._fontText_ =
                "სანსკრიტიბირმაპენჯაბისაქართველოლაოსიტამილურიარაბულიდივეჰისინდურიქურთულისოლანისპარსულიურდუუიღურულიპუშტუჩემიაპლიკაციაენა" +
                        "السنسكريتيةبورماالبنجابيةجورجيالاوسالتاميليةالعربيةالديفيهيالسنديةالكرديةسولانيالفارسيةالأرديةالأويغوريةالباشتوتطبيقيلغةيو" +
                        "سنسڪرتبرماپنجابيجارجيالاوستاملعربيڏيهيسنڌيکردشسولانيفارسياردواويغورپشتومنهنجيايپيوٻولي" +
                        "سانسکریتبۆرماپەنجابیجۆرجیالائۆستامیلعەرەبیسندیکوردیسۆلانیفارسیئوردوئویغورپشتۆ" +
                        "سانسکریتبرمهپنجابیگرجستانلائوستامیلعربیدیوهیسندیکردیسولانیفارسیاردواویغوریپشتومنبرنامهیوزبان" +
                        "سنسکرتبرماپنجابیجارجیالاؤستاملعربیڈیویہیسندھیکردسولانیفارسیاردوایغورپشتومیریایپیوزبان" +
                        "سانسكرىتبېرماپەنجاپگرۇزىيەلائوستامىلئەرەبتىلىكۇردچەسولانىپارسچەئوردۇچەئۇيغۇرپۇشتۇمېنىڭئەپىميۈتىلى" +
                        "سنسکرتبرماپنجابیجورجیالاوستاملعربیدیویسندیکردسولانیفارسیاردواویغورپشتوزماایپیوژبه" +
                        "龘靐齉齾龗麤鱻爩龖吁灪麣鸾鹂鲡驫饢籱癵爨滟厵麷鸜郁骊钃讟虋纞龞齽齼鼺黸麢鹳鹦鸙鸘";

        // 简单模式，方便你混淆后查看项目混淆情况；
        //this._fontText_ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        _NamespacesSize = 3;
        if(isTest) {
            _fontText = _fontText2;
        }

        init();
    }
    public V3SrcConfused(String 自定义命名字符, boolean 简单模式, int 命名长度)
    {
        this._fontText_ = 自定义命名字符;
        this._NamespacesSize = 命名长度;
        if(简单模式)
        {
            _fontText = _fontText2;
        }
        if(isTest) {
            _NamespacesSize = 3;
            _fontText = _fontText2;
        }
        init();
    }

    /**
     * 开始混淆
     * */
    public void 开始混淆(String 输入目录, String 输出目录, boolean 是否处理完整项目)
    {
        this.javadir = 输出目录;
        DirDelete(javadir);
        if(是否处理完整项目)
            CopyDirectory(输入目录, javadir, true);
        DirDelete(javadir + "/src");

        if(isTest) {
            _NamespacesSize = 3;
        }
        toCodeFileS(输入目录 + "/src");
    }
    private void init()
    {
        for(int i=0; i<20; i++)
        {
            javac.add(new JavaCode(getRandomFontTextPackageNames()));
            myus2.add(new MyuInfo(getRandomFontTextNotRepeating()));
        }
    }

    // 日志
    public StringBuilder logs = new StringBuilder();
    // 是否开启日志
    public boolean islog = false;
    public void log(Object o)
    {
        if(islog) {
            System.out.println(o);
            logs.append(o).append('\n');
        }
    }
    // 获取日志
    public String log()
    {
        return logs.toString();
    }

    public ArrayList<JavaCode> javac = new ArrayList<>();
    public class JavaCode
    {
        public String PackageName;
        public HashMap<String, String> methods = new HashMap<>();
        public JavaCode(String PackageName)
        {
            this.PackageName = PackageName;
        }
    }
    public JavaCode getJavaCode()
    {
        Random r = new Random();
        return javac.get(r.nextInt(javac.size()));
    }

    public void toCodeFileS(String dir)
    {
        File[] fs = new File(dir).listFiles();
        if(fs != null)
        {
            String path, path2;
            for(File all : fs)
            {
                path = all.getPath();
                path2 = path.toLowerCase();
                if(path2.endsWith(".myu"))
                    myu(path);
                else if(!path2.endsWith(".iyu"))
                {
                    CopyFile(path, javadir + "/src/" + all.getName(), true);
                }
            }

            String f;
            for(MyuInfo all : myus.values())
            {
                all.tc.Go();
                f = javadir + "/src/" + all.name + ".myu";
                if(isTest)
                    log(f + ":" + all.tc.getSb());
                CreateDir(f, false);
                WriteSdTextFile(f, all.tc.getSb());
            }
            for(File all : fs)
            {
                path = all.getPath();
                path2 = path.toLowerCase();
                if(path2.endsWith(".iyu"))
                    iyu(path, javadir + "/src/" + all.getName());
            }

            for(MyuInfo all : myus2)
            {
                if(all.sb.length() > 0)
                {
                    all.tc = new ToCode(all.sb.toString(), null);
                    all.tc.Go();
                    f = javadir + "/src/" + all.name + ".myu";
                    if(isTest)
                        log(f + ":" + all.tc.getSb());

                    CreateDir(f, false);
                    WriteSdTextFile(f, all.tc.getSb());

                }
            }

            int i2;
            StringBuilder sb = new StringBuilder();
            for(JavaCode all : javac)
            {
                if(all.methods.size() > 0)
                {
                    sb.setLength(0);
                    i2 = all.PackageName.lastIndexOf('.');
                    sb.append("package "+ all.PackageName.substring(0, i2) +";\n\npublic class "+ all.PackageName.substring(i2+1) +" {\n");
                    for(String all2 : all.methods.values())
                    {
                        sb.append(all2);
                    }
                    sb.append("\n}\n");
                    f = javadir + "/_bin_/java/" + all.PackageName.replace('.', '/') + ".java";
                    if(isTest)
                        log(f + ":" + sb.toString());

                    CreateDir(f, false);
                    WriteSdTextFile(f, sb.toString());

                }
            }
        }
    }

    public class MyuInfo
    {
        public StringBuilder sb = new StringBuilder();
        public String name;
        public ToCode tc = null;
        public HashMap<String, String> methods = new HashMap<>();
        public MyuInfo(String name, String text)
        {
            this.name = name;
            tc = new ToCode(text, this);
        }

        public MyuInfo(String name)
        {
            this.name = name;
        }

        private String ObfuscateMethods(String s)
        {
            String v = methods.get(s);
            if(v == null)
            {
                v = getRandomFontTextNotRepeating();
                methods.put(s, v);
            }
            return v;
        }
    }

    private ArrayList<MyuInfo> myus2 = new ArrayList<>();
    private HashMap<String, MyuInfo> myus = new HashMap<>();
    public void myu(String file)
    {
        MyuInfo mi = null;
        String name = file.replace('\\', '/');
        int i2 = name.lastIndexOf('/');
        if(i2 != -1)
        {
            String s = name.substring(i2 + 1, name.length() - 4).trim();
            mi = myus.get(s);
            //System.out.println(s + ":A:" + mi);
            if(mi == null)
            {
                mi = new MyuInfo(getRandomFontTextNotRepeating(), ReadSdTextFile(file));
                myus.put(s, mi);
                //System.out.println(s + ":B:" + myus.get(s));
                //if(isTest)
                //    System.out.println(":" + mi);
            }
        }
    }

    public MyuInfo TemporaryMyu(String name, String st)
    {
        Random r = new Random();
        MyuInfo mi = myus2.get(r.nextInt(myus2.size()));
        mi.sb.append("\n" + name + "\n" + st + "\nend fn\n");
    	/*MyuInfo mi = new MyuInfo(name2, new ToCode(new StringBuilder().append(name).append("\n").append(st).append("\nend fn\n").toString()).Go());
		myus.put(name2, mi);*/
        return mi;
    }

    public void iyu(String file, String file2)
    {
        String xml = ReadSdTextFile(file);
        StringBuilder sb = new StringBuilder();
        String UIEventset = JieQuStr(xml, "<UIEventset>", "</UIEventset>");

        int idMax = 1;
        ArrayList<ViewInfo> map = new ArrayList<>();
        String[] sz = split(xml, "</View>");
        for(String all : sz)
        {
            if(all.contains("<View "))
            {
                int id = StringToInt(JieQuStr(all, "id=\"", "\""), -1);
                if(id != -1){
                    if(id >= idMax)
                        idMax = id + 1;
                    String type = JieQuStr(all, "type=\"", "\"");
                    if(type != null)
                        map.add(new ViewInfo(sb, id, type, all));
                }
            }
        }

        if(UIEventset != null){
            sb.append("<UIEventset>");
            getEvent(sb, UIEventset, 0, true);
            sb.append("</UIEventset>");
        }

        if(isTest)
            log(file2 + ":" + sb.toString());

        WriteSdTextFile(file2, sb.toString());
    }

    public class ViewInfo
    {
        public int id;
        public int did;
        public String Xml;
        public String type;
        public String ppt;
        public String event;
        public StringBuilder sb;
        public StringBuilder sbPpt = new StringBuilder();
        public StringBuilder sbEvent = new StringBuilder();
        public ViewInfo(StringBuilder sb, int id, String type, String xml)
        {
            this.sb = sb;
            this.id = id;
            this.did = StringToInt(JieQuStr(xml, "did=\"", "\""), 0);
            this.type = type;

            this.ppt = JieQuStr(xml, "<ppt>", "</ppt>");
            this.event = JieQuStr(xml, "<event>", "</event>");
            this.sb.append("<View id=\""+ this.id +"\" did=\""+ this.did +"\" type=\""+ this.type +"\"><ppt>"+ this.ppt +"</ppt><event>");
            getEvent();
            this.sb.append("</event></View>");
        }

        public void getEvent()
        {
            if(event != null)
                V3SrcConfused.this.getEvent(this.sb, event, this.id, false);
        }
    }

    public void getEvent(StringBuilder sb, String event, int id, boolean UIevent)
    {
        String[] sz = split(event, "</eventItme>");
        for(String all : sz)
        {
            if(all.contains("<eventItme "))
            {
                String name = JieQuStr(all, "type=\"", "\"");
                if(name != null){
                    String code = JieQuStr(all, ">", null);
                    if(code != null)
                    {
                        if(UIevent && "menu".equals(name))
                        {
                            sb.append("<eventItme type=\""+ name +"\">");
                            getMenu(sb, code);
                            sb.append("</eventItme>");
                            continue;
                        }
                        String params = null;
                        if("loading".equals(name)){
                            params = "st_vW,st_lS,st_pN";
                        }
                        else if("downkey".equals(name)){
                            params = "st_kC,st_eA,st_eR";
                        }
                        else if("upkey".equals(name)){
                            params = "st_kC,st_eA,st_eR";
                        }
                        else if("onactivityresult".equals(name)){
                            params = "st_sC,st_lC,st_iT";
                        }
                        else if("clicki".equals(name)){
                            params = "st_vId,st_vW";
                        }
                        else if("touchmonitor".equals(name)){
                            params = "st_vId,st_vW,st_eA,st_eX,st_eY,st_rX,st_rY";
                        }
                        else if("press".equals(name)){
                            params = "st_vId,st_vW";
                        }
                        else if("keyboard".equals(name)){
                            params = "st_vId,st_vW,st_kC,st_eA,st_eR";
                        }
                        else if("focuschange".equals(name)){
                            params = "st_vId,st_vW,st_hF";
                        }
                        else if("editormonitor".equals(name)){
                            params = "st_vId,st_vW,st_aI,st_eA,st_eR,st_eK";
                        }
                        else if("ontextchanged".equals(name)){
                            params = "st_vId,st_vW,st_sS,st_sT,st_bE,st_cT";
                        }
                        else if("beforetextchanged".equals(name)){
                            params = "st_vId,st_vW,st_sS,st_sT,st_cT,st_aR";
                        }
                        else if("aftertextchanged".equals(name)){
                            params = "st_vId,st_vW,st_sS";
                        }
                        else if("onscrollstatechanged".equals(name)){
                            params = "st_vId,st_vW,st_sE";
                        }
                        else if("onscrolled".equals(name)){
                            params = "st_vId,st_vW,st_fM,st_vT,st_bT";
                        }
                        else if("onscrollstatechanged".equals(name)){
                            params = "st_vId,st_vW,st_sE";
                        }
                        else if("onscrolled".equals(name)){
                            params = "st_vId,st_vW,st_fM,st_vT,st_bT,st_dX,st_dY,st_isB";
                        }
                        else if("clickitem".equals(name)){
                            params = "st_vId,st_vW,st_pN,st_iD";
                        }
                        else if("onprogresschanged".equals(name)){
                            params = "st_vId,st_vW,st_nS";
                        }
                        else if("shouldoverrideurlloading".equals(name)){
                            params = "st_vId,st_vW,st_url";
                        }
                        else if("onpagestarted".equals(name)){
                            params = "st_vId,st_vW,st_url,st_bP";
                        }
                        else if("onpagefinished".equals(name)){
                            params = "st_vId,st_vW,st_url";
                        }
                        else if("onreceivederror".equals(name)){
                            params = "st_vId,st_vW,st_eE,description,failingUrl";
                        }
                        else if("ondownloadstart".equals(name)){
                            params = "st_vId,st_vW,st_url,st_uT,st_cN,st_mE,st_cH";
                        }
                        else if("onpageselected".equals(name)){
                            params = "st_vId,st_vW,st_pN";
                        }
                        else if("onpagescrolled".equals(name)){
                            params = "st_vId,st_vW,st_pN,st_pT,st_pS";
                        }
                        else if("onpagescrollstatechanged".equals(name)){
                            params = "st_vId,st_vW,st_sE";
                        }
                        else if("onpageselected".equals(name)){
                            params = "st_vId,st_vW,st_pN";
                        }
                        else if("onpagescrolled".equals(name)){
                            params = "st_vId,st_vW,st_pN,st_pT,st_pS";
                        }
                        else if("onpagescrollstatechanged".equals(name)){
                            params = "st_vId,st_vW,st_sE";
                        }
                        else if("ondrawerclosed".equals(name)){
                            params = "st_vId,st_vW,st_dW";
                        }
                        else if("ondraweropened".equals(name)){
                            params = "st_vId,st_vW,st_dW";
                        }
                        else if("onitemselected".equals(name)){
                            params = "st_vId,st_vW,st_vW2,st_pN,st_iD";
                        }
                        else if("onnothingselected".equals(name)){
                            params = "st_vId,st_vW";
                        }
                        else if("onstarttrackingtouch".equals(name)){
                            params = "st_vId,st_vW";
                        }
                        else if("onstoptrackingtouch".equals(name)){
                            params = "st_vId,st_vW";
                        }
                        else if("onprogresschanged2".equals(name)){
                            params = "st_vId,st_vW,st_nS,st_fR";
                        }
                        else if("ontabselected".equals(name)){
                            params = "st_vId,st_vW,st_pN,st_tT,st_taB";
                        }
                        else if("ontabunselected".equals(name)){
                            params = "st_vId,st_vW,st_pN,st_tT,st_taB";
                        }
                        else if("ontabreselected".equals(name)){
                            params = "st_vId,st_vW,st_pN,st_tT,st_taB";
                        }
                        else if("onrefresh".equals(name)){
                            params = "st_vId,st_vW";
                        }
                        else if("oncheckedchanged".equals(name)){
                            params = "st_vId,st_vW,st_iC";
                        }
                        else if("onoffsetchanged".equals(name)){
                            params = "st_vId,st_vW,st_vO";
                        }
                        String v = getRandomFontTextNotRepeating();
                        String sv = v + (params == null ? "()" : "("+ params +")");
                        MyuInfo mi = TemporaryMyu("fn " + sv, code);
                        sb.append("<eventItme type=\""+ name +"\">").append("fn " + mi.name + "." + sv).append("</eventItme>");
                        //sb.append("<eventItme type=\""+ name +"\">").append(new ToCode(code).Go().sb).append("</eventItme>");
                    }
                }
            }
        }
    }

    private void getMenu(StringBuilder sb, String code)
    {
        int i2, i3;
        String[] sz = ("m\n" + code).split("\ncase ", -1);
        for(int i=1; i<sz.length; i++)
        {
            i2 = sz[i].indexOf(':');
            if(i2 != -1)
            {
                i3 = sz[i].indexOf("\nbreak");
                if(i3 != -1)
                    sb.append("\ncase ").append(sz[i].substring(0, i2)).append(":\n").append(new ToCode(sz[i].substring(i2 + 1, i3)).Go().getSb()).append("\nbreak");
            }
        }
        String load = JieQuStr(code, "\ndefault:", "\nbreak");
        if(load != null)
            sb.append("\ndefault:\n").append(new ToCode(load).Go().getSb()).append("\nbreak");
    }

    private HashMap<String, String> dimsS = new HashMap<>();
    private HashMap<String, String> dimsSS = new HashMap<>();
    public class ToCode
    {
        private HashMap<String, String> dims = new HashMap<>();
        private String code;
        private MyuInfo mi = null;
        private ArrayList<String> clist = new ArrayList<String>();
        private StringBuilder csb = new StringBuilder(), temporarySb = new StringBuilder();
        public ToCode(String code, MyuInfo mi)
        {
            this.mi = mi;
            this.code = code.replace("&lt;", "<").replace("&gt;", ">");
            String[] sz = split(this.code, '\n');
            ArrayList<String> list = new ArrayList<String>();
            list.addAll(Arrays.asList(sz));
            To(list);
        }
        public ToCode(String code)
        {
            this(code, null);
        }
        public void line(String c)
        {
            if(mi != null)
            {
                if(c.startsWith("fn "))
                    c_fn2(c.substring(3));
            }
            clist.add(c);
        }
        public ToCode Go()
        {
            for(String c : clist)
            {
                line2(c);
            }
            return this;
        }
        public String getSb()
        {
            return csb.toString().replace("<", "&lt;").replace(">", "&gt;");
        }
        public void line2(String c)
        {
            temporarySb.setLength(0);
            if(c.startsWith("s "))
                c = c_s(c.substring(2), 0);
            else if(c.startsWith("ss "))
                c = c_s(c.substring(3), 1);
            else if(c.startsWith("sss "))
                c = c_s(c.substring(4), 2);
            else if(c.startsWith("fn "))
                c = c_fn(c.substring(3));
            else if(c.equals("end fn"))
            {
                dims.clear();
            }
            else if(c.startsWith("f("))
                c = c_wOrf("f", c);
            else if(c.startsWith("else f("))
                c = c_wOrf("else f", c);
            else if(c.startsWith("w("))
                c = c_wOrf("w", c);
            else if(c.startsWith("for("))
                c = c_for(c);
            else if(c.startsWith("ula("))
                c = c_ula(c);
            else if(c.startsWith("uht("))
                c = c_uht(c);
            else if(c.startsWith("call("))
                c = c_call(c);
            else if(c.startsWith("ss("))
                c = c_ss(c);
            else if(c.startsWith("s(") || c.startsWith("s2(") || c.startsWith("sn("))
                c = c_s2(c);
            else
                c = c_XXALL(c);
            if(temporarySb.length() > 0)
                csb.append(temporarySb);
            csb.append(c).append('\n');
        }

        private String c_call(String c)
        {
            int i0 = c.indexOf('(');
            if(i0 == -1) return c;
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            if(j2 > 2)
            {
                if("\"myu\"".equals(smS.get(1)))
                {
                    // 这里如果 call 代码 里的模块方法 用的变量储存 就会出问题
                    String c3 = ZuoYouJieQuStr(smS.get(2), '"', '"');
                    if(c3 != null)
                    {
                        int i2 = c3.indexOf('.');
                        if(i2 != -1)
                        {
                            String myu = c3.substring(0, i2).trim();
                            MyuInfo myu2 = myus.get(myu);
                            if(myu2 != null){
                                myu = myu2.name;
                                c3 = myu2.ObfuscateMethods(c3.substring(i2 + 1).trim());
                            }else
                                c3 = c3.substring(i2 + 1).trim();
                            String c5 = "\""+ myu +"."+ c3 +"\"";
                            StringBuilder sb = new StringBuilder().append("call(" + ObfuscateParam(smS.get(0)) + "," + ObfuscateParam(smS.get(1)) + "," + ObfuscateParam(c5));
                            for(int i = 3; i<j2; i++)
                            {
                                sb.append(",").append(ObfuscateParam(smS.get(i)));
                            }
                            return sb.append(")").toString();
                        }else
                            return c;
                    }else
                        return c;
                }
            }
            StringBuilder sb = new StringBuilder().append("call(");
            for(int i = 0; i<j2; i++)
            {
                if(i != 0)
                    sb.append(",");
                sb.append(ObfuscateParam(smS.get(i)));
            }
            return sb.append(")").toString();
        }

        private String c_fn(String c)
        {
            int i3 = c.indexOf('(');
            if(i3 == -1) return c;
            String name = c.substring(0, i3);
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            int i2 = name.indexOf('.');

            if(i2 == -1)
            {
                if(mi != null)
                {
                    name = mi.ObfuscateMethods(name);
                }
                StringBuilder sb = new StringBuilder().append("fn " + name + "(");
                for(int i = 0; i<j2; i++)
                {
                    if(i != 0)
                        sb.append(",");
                    sb.append(ObfuscateParam(smS.get(i), false));
                }
                sb.append(")");
                return sb.toString();
            }else{
                String myu = name.substring(0, i2).trim();
                MyuInfo myu2 = myus.get(myu);
                if(myu2 != null){
                    String s = name.substring(i2 + 1).trim();
                    String v = myu2.methods.get(s);
                    if(v == null)
                    {
                        return c;
                    }
                    myu = myu2.name;
                    name = v;
                    /*myu = myu2.name;
                    name = myu2.ObfuscateMethods(name.substring(i2 + 1).trim());*/
                }else{
                    name = name.substring(i2 + 1).trim();
                }

                StringBuilder sb = new StringBuilder().append("fn " + myu + "." + name + "(");
                for(int i = 0; i<j2; i++)
                {
                    if(i != 0)
                        sb.append(",");
                    sb.append(ObfuscateParam(smS.get(i), false));
                }
                return sb.append(")").toString();
            }
        }

        private void c_fn2(String c)
        {
            int i3 = c.indexOf('(');
            if(i3 == -1) return;
            String name = c.substring(0, i3).trim();
            int i2 = name.indexOf('.');
            if(i2 == -1)
                this.mi.ObfuscateMethods(name);
        }

        private String c_s2(String c)
        {
            int i0 = c.indexOf('(');
            if(i0 == -1) return c;
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            if(j2 == 2)
            {
                StringBuilder sb = new StringBuilder().append(c.substring(0, i0)).append("(");
                c_wOrf(sb, smS.get(0), true);
                sb.append(", "+ ObfuscateParam(smS.get(1)) +")");
                return sb.toString();
            }
            //c_caddc
            return c;
        }
        private String c_ss(String c)
        {
            int i0 = c.indexOf('(');
            if(i0 == -1) return c;
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            if(j2 == 2)
            {
                StringBuilder sb = new StringBuilder().append(c.substring(0, i0)).append("(");
                c_caddc(sb, smS.get(0));
                sb.append(", "+ ObfuscateParam(smS.get(1)) +")");
                return sb.toString();
            }
            return c;
        }
        private String c_s(String c, int type)
        {
            int i = c.indexOf('=');
            if(i != -1)
            {
                String st = c.substring(0, i).trim();
                String st2 = c.substring(i + 1).trim();
                String st3;
                if(st2.startsWith("\"") && st2.endsWith("\""))
                {
                    String names = null;
                    if(type == 0)
                        names = ObfuscateParam_SSS(st, dims);
                    else if(type == 1)
                        names = "ss." + ObfuscateParam_SSS("ss." + st, dimsS);
                    else if(type == 2)
                        names = "sss." + ObfuscateParam_SSS("sss." + st, dimsSS);
                    if(names != null){
                        /*
                        java.methods.put(v2, "public static String "+ v2 +"(){\nreturn "+ st2 +";\n}\n");
                        temporarySb.append("cls("+ jarname +", \""+ java.PackageName +"\", "+ v3 +")\n").append("javax("+ names +", null, "+ v3 +", \""+ v2 +"\")\n");
                        */

                        if(type == 0)
                            return "s " + ObfuscateParam_SSS(st, dims) + "=" + ObfuscateParam(st2, false);
                        else if(type == 1)
                            return "ss " + ObfuscateParam_SSS("ss." + st, dimsS) + "=" + ObfuscateParam(st2, false);
                        else if(type == 2)
                            return "sss " + ObfuscateParam_SSS("sss." + st, dimsSS) + "=" + ObfuscateParam(st2, false);
                    }
            		/*
            		if(type == 0)
            			return "s " + ObfuscateParam_SSS(st, dims) + "=" + ObfuscateParam(st2, false);
            		else if(type == 1)
            			return "ss " + ObfuscateParam_SSS("ss." + st, dimsS) + "=" + ObfuscateParam(st2, false);
            		else if(type == 2)
            			return "sss " + ObfuscateParam_SSS("sss." + st, dimsSS) + "=" + ObfuscateParam(st2, false);
            			*/
                }else{
                    if(type == 0)
                        return "s " + ObfuscateParam_SSS(st, dims) + "=" + ObfuscateParam(st2, false);
                    else if(type == 1)
                        return "ss " + ObfuscateParam_SSS("ss." + st, dimsS) + "=" + ObfuscateParam(st2, false);
                    else if(type == 2)
                        return "sss " + ObfuscateParam_SSS("sss." + st, dimsSS) + "=" + ObfuscateParam(st2, false);
                }
            }
            if(type == 1)
                return "ss " + c;
            else if(type == 2)
                return "sss " + c;
            else
                return "s " + c;
        }
        private String c_wOrf(String name, String c)
        {
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            if(c2.length() == 0)
                return c;
            StringBuilder sb = new StringBuilder().append(name).append("(");
            if(c.startsWith("else f("))
                c_wOrf(sb, c2, false);
            else
                c_wOrf(sb, c2, true);
            sb.append(")");
            return sb.toString();
        }
        private void c_wOrf(StringBuilder sb, String c, boolean is)
        {
            char[] sz = c.toCharArray();
            String st;
            int i, i2 = 0, j;
            while((i = getExcludeStringFindSymbol(sz, i2, sz.length)) != -1)
            {
                j = i - i2;
                if(j > 0){
                    st = String.valueOf(sz, i2, j).trim();
                    sb.append(ObfuscateParam(st, is));
                }
                sb.append(sz[i]);
                i2 = i + 1;
            }
            if(i2 != 0){
                j = sz.length - i2;
                if(j > 0){
                    st = String.valueOf(sz, i2, j).trim();
                    sb.append(ObfuscateParam(st, is));
                }
            }else
                sb.append(ObfuscateParam(c, is));
        }
        private void c_caddc(StringBuilder sb, String c)
        {
            char[] sz = c.toCharArray();
            String st;
            int i, i2 = 0, j;
            while((i = getExcludeStringFind(sz, '+', i2, sz.length)) != -1)
            {
                j = i - i2;
                if(j > 0){
                    st = String.valueOf(sz, i2, j).trim();
                    sb.append(ObfuscateParam(st));
                }
                sb.append(sz[i]);
                i2 = i + 1;
            }
            if(i2 != 0){
                j = sz.length - i2;
                if(j > 0){
                    st = String.valueOf(sz, i2, j).trim();
                    sb.append(ObfuscateParam(st));
                }
            }else
                sb.append(ObfuscateParam(c));
        }

        private String c_for(String c)
        {
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            int i = c2.indexOf(';');
            if(i != -1)
            {
                return "for(" + ObfuscateParam(c2.substring(0, i).trim()) + ";" + ObfuscateParam(c2.substring(i + 1).trim()) + ")";
            }
            return c;
        }
        private String c_uht(String c)
        {
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            if(j2 > 5)
            {
                int i2;
                String st;
                StringBuilder sb = new StringBuilder();
                sb.append("uht(" + ObfuscateParam(smS.get(0)) + "," + ObfuscateParam(smS.get(1)) + "," + ObfuscateParam(smS.get(2)) + "," +
                        ObfuscateParam(smS.get(3)) + "," + ObfuscateParam(smS.get(4)));
                for(int i = 5; i<j2; i++)
                {
                    st = smS.get(i);
                    i2 = st.indexOf('=');
                    if(i2 != -1){
                        sb.append(",").append(ObfuscateParam(st.substring(0, i2).trim())).append("=").append(ObfuscateParam(st.substring(i2 + 1).trim()));
                    }
                }
                sb.append(")");
                return sb.toString();
            }else
                return c_XXALL("uht", smS);
        }
        private String c_ula(String c)
        {
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            if(j2 == 1)
            {
                return c_XXALL("ula", smS);
            }
            else if(j2 == 2)
            {
                if("null".equals(smS.get(1)) || "clear".equals(smS.get(1)))
                    return c_XXALL("ula", smS);
            }
            else  if(j2 == 3)
            {
                if("list".equals(smS.get(1)))
                    return c_XXALL("ula", smS);
            }
            int i2;
            String st;
            StringBuilder sb = new StringBuilder();
            sb.append("ula(" + ObfuscateParam(smS.get(0)));
            for(int i = 1; i<j2; i++)
            {
                st = smS.get(i);
                i2 = st.indexOf('=');
                if(i2 != -1){
                    sb.append(",").append(ObfuscateParam(st.substring(0, i2).trim())).append("=").append(ObfuscateParam(st.substring(i2 + 1).trim()));
                }
            }
            sb.append(")");
            return sb.toString();
        }
        private String c_XXALL(String c)
        {
            int i0 = c.indexOf('(');
            if(i0 == -1) return c;
            String c2 = ZuoYouJieQuStr(c, '(', ')');
            if(c2 == null) return c;
            ArrayList<String> smS = paramSplit(c, c2);
            int j2 = smS.size();
            if(j2 == 0) return c;
            String name = c.substring(0, i0);
            StringBuilder sb = new StringBuilder().append(name + "(");
            for(int i = 0; i<j2; i++)
            {
                if(i != 0)
                    sb.append(",");
                sb.append(ObfuscateParam(smS.get(i)));
            }
            return sb.append(")").toString();
        }
        private String c_XXALL(String name, ArrayList<String> smS)
        {
            int j2 = smS.size();
            StringBuilder sb = new StringBuilder().append(name + "(");
            for(int i = 0; i<j2; i++)
            {
                if(i != 0)
                    sb.append(",");
                sb.append(ObfuscateParam(smS.get(i)));
            }
            return sb.append(")").toString();
        }

        private String ObfuscateParam(String s)
        {
            return ObfuscateParam(s, true);
        }
        private String ObfuscateParam(String s, boolean is)
        {
            char[] sz = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            String st;
            int i, i2 = 0, j;
            while((i = getExcludeStringFind(sz, '+', i2, sz.length)) != -1)
            {
                j = i - i2;
                if(j > 0){
                    st = String.valueOf(sz, i2, j).trim();
                    sb.append(ObfuscateParamX(st, is));
                }
                sb.append(sz[i]);
                i2 = i + 1;
            }
            if(i2 != 0){
                j = sz.length - i2;
                if(j > 0){
                    st = String.valueOf(sz, i2, j).trim();
                    sb.append(ObfuscateParamX(st, is));
                }
            }else
                return ObfuscateParamX(s, is);
            return sb.toString();
        }

        /**
         * 混淆参数
         * */
        private String ObfuscateParamX(String s, boolean is)
        {
            if(s.length() == 0)
                return s;
            int type = 0;
            if(s.startsWith("\""))
                type = 1;// 代表字符串
            else if(s.equals("true") || s.equals("false") || s.equals("null"))
                type = 2;
            else if(isQualifiedNamingNumeral(s, true))
                type = 3;
            else if(s.equals("activity"))
                return s;
            else if(s.startsWith("ss.")){
                return "ss." + ObfuscateDim(s, dimsS);
            }else if(s.startsWith("sss.")){
                return "sss." + ObfuscateDim(s, dimsSS);
            }else if(isUIID(s))
                return s;
            if(type > 0)
            {
                // 这里可能是 字符串 或 是否 或 null 或 整数；需要混淆的话则这里自己处理
                return s;
            }
            return ObfuscateDim(s);
        }
        private String ObfuscateParam_SSS(String s, HashMap<String, String> dims)
        {
            if(s.length() == 0)
                return s;
            int type = 0;
            if(s.startsWith("\""))
                type = 1;// 代表字符串
            else if(s.equals("true") || s.equals("false") || s.equals("null"))
                type = 2;
            else if(isQualifiedNamingNumeral(s, true))
                type = 3;
            else if(s.equals("activity"))
                return s;
            else if(s.startsWith("ss.")){
                return ObfuscateDim(s, dimsS);
            }else if(s.startsWith("sss.")){
                return ObfuscateDim(s, dimsSS);
            }else if(isUIID(s))// 注意防止出现 ss.iyu 或 sss.iyu 的界面，否则将可能 全局变量 和 界面变量  和  界面ID 混淆，如 全局变量 sss.12（当前允许设置纯数字变量） 与  界面ID  sss.12
                // 建议如果存在  ss.iyu 或 sss.iyu 的界面 应当在混淆时 提出警告。
                return s;
            if(type > 0)
            {
                return s;
            }
            return ObfuscateDim(s, dims);
        }
        private String ObfuscateParamX(String s)
        {
            return ObfuscateParamX(s, true);
        }

        private String orEncryptedString(String s, String b)
        {
            byte[] bs = s.getBytes();
            char c = b.charAt(0);
            StringBuilder sb = new StringBuilder().append("// ").append(s.replace("\n", "\\n").replace("\r", "\\r")).append("\nbyte[] bs = new byte[]{");
            for(int i2=0; i2<bs.length; i2++)
            {
                if(i2 != 0)
                    sb.append(',');
                bs[i2] ^= c;
                sb.append(bs[i2]);
            }
            sb.append("};\nchar c = b.charAt(0);\nfor(int i=0; i<bs.length; i++)\nbs[i] ^= c;\nreturn new String(bs);");
            return sb.toString();
        }

        /**
         * 转义转换
         * */
        private String dimZ(String st) {
            if(st.contains("\\"))
            {
                if(st.contains("\\\\"))
                    st = st.replace("\\\\", "\\U5c");
                if(st.contains("\\t"))
                    st = st.replace("\\t", "\t");
                if(st.contains("\\n"))
                    st = st.replace("\\n", "\n");
                if(st.contains("\\r"))
                    st = st.replace("\\r", "\r");
                if(st.contains("\\b"))
                    st = st.replace("\\b", "\b");
                if(st.contains("\\f"))
                    st = st.replace("\\f", "\f");
                if(st.contains("\\\""))
                    st = st.replace("\\\"", "\"");
                if(st.contains("\\U5c"))
                    st = st.replace("\\U5c", "\\");
            }
            return st;
        }

        /**
         * 混淆变量名
         * */
        private String ObfuscateDim(String s)
        {
            return ObfuscateDim(s, this.dims);
        }
        private String ObfuscateDim(String s, HashMap<String, String> dims)
        {
            String v = dims.get(s);
            if(v == null)
            {
                v = getRandomFontTextNotRepeating();
                dims.put(s, v);
            }
            return v;
        }

        /**
         * 临时变量
         * */
        private String TemporaryDim()
        {
            return V3SrcConfused.this.TemporaryDim(this.dims);
        }

        private ArrayList<String> paramSplit(String src, String c)
        {
            c = c.trim();
            ArrayList<String> list = new ArrayList<>();
            if(c.length() == 0)
                return list;
            String st;
            char[] sz = c.toCharArray();
            int i, i2 = 0;
            while((i = getExcludeStringFind(sz, ',', i2, sz.length)) != -1)
            {
                st = String.valueOf(sz, i2, i - i2).trim();
                list.add(st);
                i2 = i + 1;
            }
            if(i2 != 0){
                st = String.valueOf(sz, i2, sz.length - i2).trim();
                list.add(st);
            }else
                list.add(c);

            return list;
        }

        /**
         * 判断是否界面ID
         * */
        public boolean isUIID(String str)
        {
            int i2 = str.indexOf('.');
            if(i2 == -1) return false;
            if (!isQualifiedNaming(str.substring(0, i2)))
                return false;
            return isNumeric(str.substring(i2 + 1));
        }

        /**
         * 判断字符串是否为纯数字
         * */
        public boolean isNumeric(String str)
        {
            for (int i = str.length(); --i >= 0;)
            {
                if (!Character.isDigit(str.charAt(i)))
                    return false;
            }
            return true;
        }

        public ToCode To(ArrayList<String> sz)
        {
            boolean note = false;
            int kh = 0;
            String h2;
            for (String h: sz) {
                h2 = h.trim();
                if(h2.length() == 0)
                    continue;
                if (h2.startsWith(".") || h2.startsWith("//") || h2.startsWith("/.") || h2.startsWith("./") || note)
                {
                    if(h2.startsWith("/."))
                        note = true;
                    else if(h2.startsWith("./"))
                        note = false;
                    else{
                        h2 = h2.startsWith("//") ? h2 : "//" + h2;
                        //sb.append(h2).append('\n');
                    }
                    continue;
                }
                if (h2.equals("{"))
                {
                    line(h2);
                    kh++;
                    continue;
                }
                else if (h2.equals("}"))
                {
                    line(h2);
                    kh--;
                    continue;
                }else{
                    if (h2.startsWith("}")){
                        line("}");
                        kh--;
                        line(h2.substring(1).trim());
                        continue;
                    }
                    else if (h2.startsWith("{")){
                        line("{");
                        kh++;
                        line(h2.substring(1).trim());
                        continue;
                    }
                    if (h2.endsWith("}")){
                        line(h2.substring(0, h2.length() - 1).trim());
                        line("}");
                        kh--;
                        continue;
                    }
                    else if (h2.endsWith("{")){
                        line(h2.substring(0, h2.length() - 1).trim());
                        line("{");
                        kh++;
                        continue;
                    }
                }
                line(h2);
            }
            return this;
        }
    }
    private int StringToInt(String value, int notValue) {
        if(value == null)
            return notValue;
        try {
            notValue = Integer.parseInt(value);
        } catch (Exception e) {
        }
        return notValue;
    }
    public String getXmlString(String s)
    {
        s = s.replace("<", "&lt;");
        s = s.replace(">", "&gt;");
        return s;
    }
    public String getXmlStringTo(String s)
    {
        s = s.replace("&lt;", "<");
        s = s.replace("&gt;", ">");
        return s;
    }

    private ArrayList<String> _dimNamespaces = new ArrayList<>();
    public char[] _fontText = null;
    public String _fontText_ = null;
    public final char[] _fontText2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    /**
     * 生成指定长度变量命名，可能重复
     * */
    public String getRandomFontText(int size)
    {
        if(_fontText == null)
            _fontText = _fontText_.toCharArray();

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(size > 0)
        {
            sb.append(_fontText[r.nextInt(_fontText.length)]);
            size--;
        }
        return sb.toString();
    }

    public String getRandomFontTextPackageNames()
    {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        int j = r.nextInt(3) + 2;
        while(j > 0){
            String s = getRandomFontText(r.nextInt(3) + 1);
            if(!_dimNamespaces.contains(s))
            {
                sb.append(s);
                j--;
                if(j > 0)
                    sb.append('.');
            }
        }
        return sb.toString();
    }

    public String getRandomFontTextSimple(int size)
    {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(size > 0)
        {
            sb.append(_fontText2[r.nextInt(_fontText2.length)]);
            size--;
        }
        return sb.toString();
    }

    /**
     * 生成指定长度变量命名，并且唯一不重复
     * */
    public String getRandomFontTextNotRepeating()
    {
        String s = getRandomFontText(_NamespacesSize);
        if(_dimNamespaces.contains(s))
        {
            s = getRandomFontText(_NamespacesSize);
            while (_dimNamespaces.contains(s))
            {
                s = getRandomFontText(_NamespacesSize);
            }
        }
        _dimNamespaces.add(s);
        return s;
    }
    public String TemporaryDim(HashMap<String, String> dims)
    {
        String s = getRandomFontTextNotRepeating();
        dims.put(s, s);
        return s;
    }

    public String getControlsClassName2(String classname)
    {
        if(classname.equals("TextView"))
            return "文本";
        else if(classname.equals("ImageView"))
            return "图像";
        else if(classname.equals("Button"))
            return "按钮";
        else if(classname.equals("ImageButton"))
            return "图像按钮";
        else if(classname.equals("EditText"))
            return "编辑框";
        else if(classname.equals("RadioGroup"))
            return "单选布局";
        else if(classname.equals("RadioButton"))
            return "单选项";
        else if(classname.equals("CheckBox"))
            return "多选";
        else if(classname.equals("ListView"))
            return "列表";
        else if(classname.equals("WebView"))
            return "浏览器";
        else if(classname.equals("Spinner"))
            return "下拉菜单";
        else if(classname.equals("RatingBar"))
            return "评分";
        else if(classname.equals("VideoView"))
            return "视频";
        else if(classname.equals("GifView"))
            return "动态图";
        else if(classname.equals("RoundImageView"))
            return "圆形图";
        else if(classname.equals("SeekBar"))
            return "拖动条";
        else if(classname.equals("ProgressBar"))
            return "进度条";
        else if(classname.equals("DatePicker"))
            return "日期选择器";
        else if(classname.equals("TimePicker"))
            return "时间选择器";
        else if(classname.equals("GridView"))
            return "网格视图";
        else if(classname.equals("LinearLayout"))
            return "线性布局";
        else if(classname.equals("RelativeLayout"))
            return "相对布局";
        else if(classname.equals("TableLayout"))
            return "表格布局";
        else if(classname.equals("TableRow"))
            return "表格项";
        else if(classname.equals("FrameLayout"))
            return "帧布局";
        else if(classname.equals("SurfaceView"))
            return "面控件";
        else if(classname.equals("ScrollView"))
            return "滚动";
        else if(classname.equals("HorizontalScrollView"))
            return "水平滚动";
        else if(classname.equals("ViewPager"))
            return "滑动窗体";
        else if(classname.equals("DrawerLayout"))
            return "侧滑窗体";

        else if(classname.equals("CoordinatorLayout"))
            return "协调性布局";
        else if(classname.equals("AppBarLayout"))
            return "应用栏布局";
        else if(classname.equals("CollapsingToolbarLayout"))
            return "折叠工具栏布局";
        else if(classname.equals("Toolbar"))
            return "工具栏布局";
        else if(classname.equals("FloatingActionButton"))
            return "浮动动作按钮";
        else if(classname.equals("NestedScrollView"))
            return "嵌套滚动";
        else if(classname.equals("TabLayout"))
            return "标签布局";
        else if(classname.equals("RecyclerView"))
            return "v7列表";
        else if(classname.equals("ConstraintLayout"))
            return "约束性布局";
        else if(classname.equals("VerticalViewPager"))
            return "垂直滑动窗体";
        else if(classname.equals("SwipeRefreshLayout"))
            return "下拉刷新控件";
        else if(classname.equals("TextInputLayout"))
            return "文本输入布局";
        else if(classname.equals("SwitchCompat"))
            return "开关";
        else if(classname.equals("CardView"))
            return "卡片";
        return null;
    }

    private static int getExcludeStringFindSymbol(char[] sz, int i, int j)
    {
        while(i < j)
        {
            if (isSrcOperator(sz[i]))
            {
                return i;
            }
            else if (sz[i] == '"' || sz[i] == '\'')
            {
                i = getExpressionStr(sz, i, j);
                if(i == -1)// 遇到字符串错误，返回错误
                    return -1;
            }
            i++;
        }
        return -1;
    }
    /**
     * 获取表达式里的字符串 与 char 类型
     * */
    public static int getExpressionStr(char[] sz, int i, int j)
    {
        if (sz[i] == '"')
        {
            i++;
            int i2;
            boolean sfok = false;
            W: while(i < j)
            {
                if (sz[i] == '\r' || sz[i] == '\n') // 如果字符串中有换行，报错
                    return -1;
                else if (sz[i] == '"') // 字符串结束
                {
                    i2 = i - 1;
                    if(sz[i2] == '\\') {
                        i2--;
                        if(sz[i2] == '\\')
                        {
                            i2--;
                            while(i2 >= 0)
                            {
                                if(sz[i2] != '\\')
                                {
                                    i2 = i - i2;
                                    if(i2 % 2 == 1)
                                    {
                                        sfok = true;
                                        break W;
                                    }
                                    break;
                                }
                                i2--;
                            }
                        }
                    }else{
                        sfok = true;
                        break;
                    }
                }
                i++;
            }
            if(!sfok)
                return -1;
        }
        else if (sz[i] == '\'')
        {
            i++;
            if(i < j){
                if(sz[i] == '\\')
                {
                    i += 2;
                    if(i < j && sz[i] == '\'')
                        return i;
                }else{
                    i++;
                    if(i < j && sz[i] == '\'')
                        return i;
                }
            }
            return -1;
        }
        return i;
    }
    private static boolean isSrcOperator(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '=' || c == '!' || c == '&' || c == '|' || c == '<' || c == '>' || c == '(' || c == ')' || c == '[' || c == ']' || c == '?';
    }

    /**
     * 左右查询后截取相关字符串，此方法仅用于已确定字符串
     * */
    public static String ZuoYouJieQuStr(String st, char c1, char c2)
    {
        int i = st.indexOf(c1);
        if(i == -1) return null;
        int i2 = st.lastIndexOf(c2);
        if(i2 == -1) return null;
        i += 1;
        if(i > i2) return null;
        return st.substring(i, i2);
    }
    /**
     * 判断变量或 方法 命名是否合格
     * */
    public static boolean isQualifiedNaming(String s)
    {
        if (s == null || s.length() == 0)
            return false;
        char[] sz = s.toCharArray();
        if (isNameStartQualified(sz[0]))
        {
            int j = sz.length;
            for (int i = 1; i < j; i++)
            {
                if (!isNameQualified(sz[i]))
                    return false;
            }
            return true;
        }
        return false;
    }
    /**
     * 判断命名是否合格 是否小写字母 或 大写字母 或 数字 或 汉字 或 下划线
     * */
    public static boolean isNameQualified(char c)
    {
        int i = c;
        return (i >= 97 && i <= 122) || (i >= 65 && i <= 90) || (i >= 48 && i <= 57) || (c >= 0x4e00 && c <= 0x9fbb) || (c == '_' || c == '$');
    }
    /**
     * 判断命名开头是否合格 是否小写字母 或 大写字母 或 汉字 或 下划线
     * */
    public static boolean isNameStartQualified(char c)
    {
        int i = c;
        return (i >= 97 && i <= 122) || (i >= 65 && i <= 90) || (c >= 0x4e00 && c <= 0x9fbb) || (c == '_' || c == '$');
    }
    /**
     * 排除字符串 进行查询字符
     * */
    public static int getExcludeStringFind(char[] sz, char c, int i, int j)
    {
        while(i < j)
        {
            if (sz[i] == c)
            {
                return i;
            }
            else if (sz[i] == '"' || sz[i] == '\'')
            {
                i = getExpressionStr(sz, i, j);
                if(i == -1)// 遇到字符串错误，返回错误
                    return -1;
            }
            i++;
        }
        return -1;
    }

    /**
     * 判断参数 是否数字类型，symbol 是否为有符号的数字
     * */
    public static boolean isQualifiedNamingNumeral(String s, boolean symbol)
    {
        if (s == null || s.length() == 0)
            return false;
        char[] sz = s.toCharArray();
        int j = sz.length;
        if(symbol)
        {
            boolean xsd = false;
            for (int i = 0; i < j; i++)
            {
                if(sz[i] == '.'){
                    if(xsd)
                        return false;
                    xsd = true;
                }
                else if(sz[i] == '-' && i != 0)
                    return false;
                else if (!isNameQualifiedNumeral(sz[i]))
                    return false;
            }
        }else{
            for (int i = 0; i < j; i++)
            {
                if (!isNameQualifiedNumeralNotSymbol(sz[i]))
                    return false;
            }
        }
        return true;
    }
    /**
     * 判断命名是否合格 数字；有符号
     * */
    public static boolean isNameQualifiedNumeral(char c)
    {
        int i = c;
        return (i >= 48 && i <= 57) || (c == '.') || (c == '-');
    }
    /**
     * 判断命名是否合格 数字；无小数点符号
     * */
    public static boolean isNameQualifiedNumeralNotSymbol(char c)
    {
        int i = c;
        return (i >= 48 && i <= 57);
    }

    public static void DirDelete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                DirDelete(childFiles[i]);
            }
            file.delete();
        }
    }
    public static void DirDelete(String file) {
        File f = new File(file);
        if(f.exists())
            DirDelete(f);
    }
    public static boolean CopyFile(String file, String Newfile, boolean Cover) {
        boolean s = false;
        CreateDir(Newfile, false);
        File f = new File(Newfile);
        File f2 = new File(file);
        if (!f2.exists())
            return false;
        if (f.exists()) {
            if (!Cover)
                return false;
            f.delete();
        }
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(f2);
            f.createNewFile();
            os = new FileOutputStream(f);
            byte[] bytes = new byte[512];
            int i = -1;
            while ((i = is.read(bytes)) > 0) {
                os.write(bytes, 0, i);
            }
            s = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
    }


    public static boolean CreateDir(String dir, boolean s) {
        if (s)
            return new File(dir).mkdirs();
        else
            return Objects.requireNonNull(new File(dir).getParentFile()).mkdirs();
    }
    public static boolean CreateDir(File dir, boolean s) {
        if (s)
            return dir.mkdirs();
        else
            return Objects.requireNonNull(dir.getParentFile()).mkdirs();
    }


    public static void WriteSdTextFile(String file, String str, String bm) {
        File file2 = new File(file);
        CreateDir(file2, false);
		/*
		if (file2.exists())
			file2.delete();*/
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(file2), bm);
            osw.write(str);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(osw != null) {
                try {
                    osw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void WriteSdTextFile(String file, String str) {
        WriteSdTextFile(file, str, "utf-8");
    }


    private static final String EMPTY = "";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static String[] split(final String str, final char separatorChar) {
        if (str == null) {
            return null;
        }
        final boolean preserveAllTokens = false;
        final int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<String>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }
    public static String[] split(final String str, final String separator) {
        if (str == null) {
            return null;
        }
        final int max = -1;
        final boolean preserveAllTokens = false;
        final int len = str.length();

        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }

        if (separator == null || EMPTY.equals(separator)) {
            // Split on whitespace.
            return splitWorker(str, null, max, preserveAllTokens);
        }

        final int separatorLength = separator.length();

        final ArrayList<String> substrings = new ArrayList<String>();
        int numberOfSubstrings = 0;
        int beg = 0;
        int end = 0;
        while (end < len) {
            end = str.indexOf(separator, beg);

            if (end > -1) {
                if (end > beg) {
                    numberOfSubstrings += 1;

                    if (numberOfSubstrings == max) {
                        end = len;
                        substrings.add(str.substring(beg));
                    } else {
                        // The following is OK, because String.substring( beg,
                        // end ) excludes
                        // the character at the position 'end'.
                        substrings.add(str.substring(beg, end));

                        // Set the starting point for the next search.
                        // The following is equivalent to beg = end +
                        // (separatorLength - 1) + 1,
                        // which is the right calculation:
                        beg = end + separatorLength;
                    }
                } else {
                    // We found a consecutive occurrence of the separator, so
                    // skip it.
                    if (preserveAllTokens) {
                        numberOfSubstrings += 1;
                        if (numberOfSubstrings == max) {
                            end = len;
                            substrings.add(str.substring(beg));
                        } else {
                            substrings.add(EMPTY);
                        }
                    }
                    beg = end + separatorLength;
                }
            } else {
                // String.substring( beg ) goes from 'beg' to the end of the
                // String.
                substrings.add(str.substring(beg));
                end = len;
            }
        }

        return substrings.toArray(new String[substrings.size()]);
    }
    private static String[] splitWorker(final String str,
                                        final String separatorChars, final int max,
                                        final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    public static String JieQuStr(String str, String str_a, String str_b) {
        int JieQuStr_i = 0;
        int JieQuStr_j = 0;
        if (str_a != null) {
            JieQuStr_i = str.indexOf(str_a);
            if (JieQuStr_i == -1)
                return null;
            JieQuStr_i += str_a.length();
        }
        if (str_b == null)
            JieQuStr_j = str.length();
        else {
            JieQuStr_j = str.indexOf(str_b, JieQuStr_i);
            if (JieQuStr_j == -1)
                return null;
        }
        return str.substring(JieQuStr_i, JieQuStr_j);
    }

    public static String ReadSdTextFile(String file, String bm) {
        File file2 = new File(file);
        if (!file2.exists())
            return null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        StringBuffer strs = new StringBuffer();
        String filecontent = null;
        try {
            fis = new FileInputStream(file2);
            isr = new InputStreamReader(fis, bm);
            char[] Char = new char[512];
            int i = -1;
            while ((i = isr.read(Char)) > 0) {
                strs.append(Char, 0, i);
            }
            filecontent = new String(strs);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return filecontent;
    }

    public static String ReadSdTextFile(String file) {
        return ReadSdTextFile(file, "utf-8");
    }
    public static int CopyDirectory(String dir, String dir2, boolean s) {
        File dirFile = new File(dir);
        File dir2File = new File(dir2);
        return CopyDirectory(dirFile, dir2File, s);
    }
    public static int CopyDirectory(File dirFile, File dir2File, boolean s) {
        String dir2FilePath = dir2File.getAbsolutePath();
        if(dirFile.getAbsolutePath().equals(dir2FilePath))
            return 0;
        int count = 0;
        File[] childFiles = dirFile.listFiles();
        if (childFiles != null)
        {
            for(int i=0; i<childFiles.length; i++)
            {
                if (childFiles[i].isFile()){
                    if(CopyFile(childFiles[i].getAbsolutePath(), String.format("%s/%s", dir2FilePath, childFiles[i].getName()), s))
                        count++;
                }
                else if (childFiles[i].isDirectory()){
                    count += CopyDirectory(new File(String.format("%s/%s", dirFile.getAbsolutePath(), childFiles[i].getName())), new File(String.format("%s/%s", dir2File.getAbsolutePath(), childFiles[i].getName())), s);
                }
            }
        }
        return count;
    }
}