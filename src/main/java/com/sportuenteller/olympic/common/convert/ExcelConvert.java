package com.sportuenteller.olympic.common.convert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

abstract public class ExcelConvert<D,R,W> {

    public List<D> read(File excel, List<D> domains) {
        List<D> registers = new ArrayList<>();

        List<R> excels = this.convert(this.readExcel(excel));

        for(R excelData : excels){
            if(this.checkModify(excelData)){
                D originalCountry = this.findDomain(domains, excelData);
                if(originalCountry != null){
                    this.modify(originalCountry, excelData);
                }else{
                    registers.add(this.createDomain(excelData));
                }
            }
        }
        return registers;
    }

    public File write(List<D> domains) {
        List<W> requests = new ArrayList<>();
        for(D domain : domains){
            requests.addAll(this.convert(domain));
        }
        return this.writeExcel(requests);
    }

    abstract protected List<R> convert(List<R> excelDataList);
    abstract protected List<R> readExcel(File excel);
    abstract protected File writeExcel(List<W> requests);
    abstract protected boolean checkModify(R excelData);
    abstract protected D findDomain(List<D> domains, R excelData);
    abstract protected void modify(D domain, R excelData);
    abstract protected D createDomain(R excelData);
    abstract protected List<W> convert(D domain);

}
