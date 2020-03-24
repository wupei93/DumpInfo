package wupei.dumpinfo.common.dt;


import static wupei.dumpinfo.common.dt.DtType.*;

public enum DtKey {
    CHUNK(CT),
    OBJECT_TABLE_KEY(OB),
    REPO_REFERENCE(RR);

    private DtType dtType;
    DtKey(DtType dtType){
        this.dtType = dtType;
    }

    public DtType getDtType() {
        return dtType;
    }
}
