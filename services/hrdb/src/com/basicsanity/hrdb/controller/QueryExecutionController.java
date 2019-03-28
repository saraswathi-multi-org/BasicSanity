/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.basicsanity.hrdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.basicsanity.hrdb.Vacation;
import com.basicsanity.hrdb.service.HrdbQueryExecutorService;
import com.basicsanity.hrdb.models.query.*;

@RestController(value = "Hrdb.QueryExecutionController")
@RequestMapping("/hrdb/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private HrdbQueryExecutorService queryService;

    @Autowired
	private ExportedFileManager exportedFileManager;

    @RequestMapping(value = "/queries/HQL_FromVacation", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "HQL_FromVacation")
    public Page<Vacation> executeHQL_FromVacation(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: HQL_FromVacation");
        Page<Vacation> _result = queryService.executeHQL_FromVacation(pageable);
        LOGGER.debug("got the result for named query: HQL_FromVacation, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query HQL_FromVacation")
    @RequestMapping(value = "/queries/HQL_FromVacation/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportHQL_FromVacation(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: HQL_FromVacation");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "HQL_FromVacation";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportHQL_FromVacation( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/SV_EmpData", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "SV_EmpData")
    public Page<SvEmpDataResponse> executeSV_EmpData(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: SV_EmpData");
        Page<SvEmpDataResponse> _result = queryService.executeSV_EmpData(pageable);
        LOGGER.debug("got the result for named query: SV_EmpData, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query SV_EmpData")
    @RequestMapping(value = "/queries/SV_EmpData/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportSV_EmpData(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: SV_EmpData");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "SV_EmpData";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportSV_EmpData( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

}