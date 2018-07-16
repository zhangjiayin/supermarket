$(function(){
	
    //点击查询
    $("#queryList").click(function(){
		$.ajax({
			   type: "POST",
			   url: "rest/cim/crmcfplevelrecord/queryStatisticsList",
			   data: {startTime:$("#startTime").val(),endTime:$("#endTime").val()},
			   success: function(statisList){
				   var html = '';
				   for(var index in  statisList){
					   html += '<tr role="row" class="odd selected">';
					   html += '<td>' +  statisList[index].month + '</td>';
					   html += '<td>' +  statisList[index].xsyjStaticCount + '</td>';
					   html += '<td>' +  statisList[index].tjjlStaticCount + '</td>';
					   html += '<td>' +  statisList[index].zjgljtStaticCount + '</td>';
					   html += '<td>' +  statisList[index].tdgljtStaticCount + '</td>';
					   html += '<td>' +  statisList[index].sm3StaticCount + '</td>';
					   html += '<td>' +  statisList[index].sm2StaticCount + '</td>';
					   html += '<td>' +  statisList[index].sm1StaticCount + '</td>';
					   html += '<td>' +  statisList[index].taStaticCount + '</td>';
					   html += '</tr>';
				   }
				   $('tbody').html(html);
				   
				   //绘制图表
				   initEcharts(statisList);
			   },
			   error: function (XMLHttpRequest, textStatus, errorThrown) {
				   bootbox.alert("查询异常");
			}
		}); 	
    });
    
    $("#queryList").trigger('click');
});

//绘制图表
function initEcharts(statisList){
	
    // 基于准备好的dom，初始化echarts实例
    var awardChart = echarts.init($('#awardEveryMonth')[0]);
    var levelChart = echarts.init($('#levelEveryMonth')[0]);
    
    var awardSeriesData = new Array();
    var awardLegendData = new Array();
    
    var levelSeriesData = new Array();
    var levelLegendData = new Array();
    
    for(var index in  statisList){
    	
    	//每月获得奖励及津贴的人数
    	var awardSeriesObj = new Object();
    	awardSeriesObj.name = statisList[index].month;
    	awardSeriesObj.type = 'line';
    	var awardSeriesObjData = new Array();
    	awardSeriesObjData.push(statisList[index].xsyjStaticCount);
    	awardSeriesObjData.push(statisList[index].tjjlStaticCount);
    	awardSeriesObjData.push(statisList[index].zjgljtStaticCount);
    	awardSeriesObjData.push(statisList[index].tdgljtStaticCount);
    	awardSeriesObj.data = awardSeriesObjData,
    	
    	awardLegendData.push(String(statisList[index].month));
    	awardSeriesData.push(awardSeriesObj);
    	
    	//每月各职级人数情况
    	var levelSeriesObj = new Object();
    	levelSeriesObj.name = statisList[index].month;
    	levelSeriesObj.type = 'line';
    	var levelSeriesObjData = new Array();
    	levelSeriesObjData.push(statisList[index].sm3StaticCount);
    	levelSeriesObjData.push(statisList[index].sm2StaticCount);
    	levelSeriesObjData.push(statisList[index].sm1StaticCount);
    	levelSeriesObjData.push(statisList[index].taStaticCount);
    	levelSeriesObj.data = levelSeriesObjData,
    	
    	levelLegendData.push(String(statisList[index].month));
    	levelSeriesData.push(levelSeriesObj);
    }

    // 指定图表的配置项和数据
    var awardOption = {
    	    tooltip: {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data:awardLegendData
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    toolbox: {
    	        feature: {
    	            saveAsImage: {}
    	        }
    	    },
    	    xAxis: {
    	        type: 'category',
    	        boundaryGap: true,
    	        data: ['销售佣金','推荐奖励','直接管理津贴','团队管理津贴']
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: awardSeriesData
     };
    
    var levelOption = {
    	    tooltip: {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data:levelLegendData
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    toolbox: {
    	        feature: {
    	            saveAsImage: {}
    	        }
    	    },
    	    xAxis: {
    	        type: 'category',
    	        boundaryGap: true,
    	        data: ['总监','经理','顾问','见习']
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: levelSeriesData
     };  

    console.log(awardOption);
    // 使用刚指定的配置项和数据显示图表。
    awardChart.setOption(awardOption);
    // 使用刚指定的配置项和数据显示图表。
    levelChart.setOption(levelOption);
}