package com.tc.edu.tc.MyProject._Test;

import com.tc.edu.tc.MyBase.CMyDataService;

public class CHead2PullDownService extends CMyDataService {
    @Override
    public String getAction(){
        return "rcvAction-CHead2PullDownService";
    }

    @Override
    protected String getJsonData() {
        String jsonData = "{\"menus\":{\"1\":{\"menu\":\"学前\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]}}},\"2\":{\"menu\":\"小学\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]}}},\"3\":{\"menu\":\"小升初\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]}}},\"4\":{\"menu\":\"初中\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]}}},\"5\":{\"menu\":\"中考\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]}}},\"6\":{\"menu\":\"高中\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]},\"5\":{\"menu\":\"化学\",\"paras\":\"\",\"sub\":[]}}},\"7\":{\"menu\":\"高考\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]},\"5\":{\"menu\":\"化学\",\"paras\":\"\",\"sub\":[]}}},\"8\":{\"menu\":\"艺术\",\"paras\":\"\",\"sub\":{\"6\":{\"menu\":\"舞蹈\",\"paras\":\"\",\"sub\":[]},\"7\":{\"menu\":\"乐器\",\"paras\":\"\",\"sub\":[]},\"8\":{\"menu\":\"歌唱\",\"paras\":\"\",\"sub\":[]}}},\"9\":{\"menu\":\"其它\",\"paras\":\"\",\"sub\":{\"\":{\"menu\":null,\"paras\":\"\",\"sub\":[]}}}},\"time\":{\"1\":{\"menu\":\"全天\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"上午\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"下午\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"晚上\",\"paras\":\"\",\"sub\":[]}}}";
        return jsonData;
    }
}
