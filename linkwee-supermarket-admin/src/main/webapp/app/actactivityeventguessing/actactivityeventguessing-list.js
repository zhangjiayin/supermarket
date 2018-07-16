var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/act/actactivityeventguessing/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/act/actactivityeventguessing/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/act/actactivityeventguessing/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },
        table: "#event_guessing_list",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "新增",
                "title":  "创建新的实体",
                "submit": "确定"
            },
            "edit": {
                "button": "编辑",
                "title":  "编辑实体",
                "submit": "确定"
            },
            "remove": {
                "button": "删除",
                "title":  "删除",
                "submit": "确定",
                "confirm": {
                    "_": "确定要删除选择的 %d 行数据吗?",
                    "1": "确定要删除选择的 1 行数据吗?"
                }
            },
         
            "error": {
                "system": "发生系统错误 (More information)"
            },
         
            "multi": {
                "title": "多个值",
                "info": "选择的内容中当前输入框包含不同的值. 把他们设置成相同的值, 点击这里, 否则它们仍然保留不同的值.",
                "restore": "取消改动"
            },
         
            "datetime": {
                "previous": '前',
                "next":     '后',
                "months":   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                "weekdays": [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
                "amPm":     [ '上午', '下午' ],
                "unknown":  '-'
            }
        },
        fields: [
         {
                label: "自增长主键:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "活动编码:",
                name: "activityCode"
            }, 
         {
                label: "竞猜名称:",
                name: "guessingName"
            }, 
         {
                label: "奖励发放状态(0:未发放，1:已发放):",
                name: "grantStatus"
            }, 
         {
                label: "发放奖励需要的得分:",
                name: "couldGrantScore"
            }, 
         {
                label: "奖池:",
                name: "jackpot"
            }, 
         {
                label: "比赛方A:",
                name: "competitionPartyA"
            }, 
         {
                label: "比赛方B:",
                name: "competitionPartyB"
            }, 
         {
                label: "A方得分:",
                name: "scoreA"
            }, 
         {
                label: "B方得分:",
                name: "scoreB"
            }, 
         {
                label: "A方支持票数:",
                name: "supportVotesA"
            }, 
         {
                label: "B方支持票数:",
                name: "supportVotesB"
            }, 
         {
                label: "A方支持目标票数:",
                name: "supportVotesTargetA"
            }, 
         {
                label: "B方支持目标票数:",
                name: "supportVotesTargetB"
            }, 
         {
                label: "A方支持票数增长率:",
                name: "growthRateA"
            }, 
         {
                label: "B方支持票数增长率:",
                name: "growthRateB"
            }, 
         {
                label: "下一场开赛时间:",
                name: "nextStartTime"
            }, 
         {
                label: "投票开始时间:",
                name: "voteStartTime"
            }, 
         {
                label: "投票结束时间:",
                name: "voteEndTime"
            },
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var name = editor.field( 'name' );
 
            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            if ( ! name.isMultiValue() ) {
                if ( ! name.val() ) {
                	name.error( 'A activity name must be given' );
                }
                 
                if ( name.val().length <= 2 ) {
                	name.error( 'The activity name length must be more that 2 characters' );
                }
            }
 
            // ... additional validation rules
 
            // If any error was reported, cancel the submission so it can be corrected
            if ( this.inError() ) {
                return false;
            }
        }
    } );

    changeScoreTable = $('#event_guessing_list').DataTable( {
    	dom: "Bfrtip",
        "processing": true,
        "serverSide": true,
        "language": {
        	select: {
                rows: {
                    _: "已选择 %d 行",
                    1: "已选择 1 行"
                }
            },
        	"sProcessing":   "处理中...",
        	"sLengthMenu":   "显示 _MENU_ 项结果",
        	"sZeroRecords":  "没有匹配结果",
        	"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        	"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
        	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        	"sInfoPostFix":  "",
        	"sSearch":       "搜索:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	},
        	"oAria": {
        		"sSortAscending":  ": 以升序排列此列",
        		"sSortDescending": ": 以降序排列此列"
        	}
        },
        "ajax": {
            "url": "/rest/act/actactivityeventguessing/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "guessingName","orderable": false },
            { "data": "grantStatus","orderable": false,"render": function ( data, type, row ) {
                    if(data == 0){
                        return "未发放";
                    }else if(data == 1){
                        return "已发放";
                    }
                }
            },
            { "data": "couldGrantScore","orderable": false },
            { "data": "jackpot","orderable": false },
            { "data": "competitionPartyA","orderable": false },
            { "data": "competitionPartyB","orderable": false },
            { "data": "scoreA","orderable": false },
            { "data": "scoreB","orderable": false },
            { "data": "supportVotesA","orderable": false },
            { "data": "supportVotesB","orderable": false },
            { "data": "supportVotesTargetA","orderable": false },
            { "data": "supportVotesTargetB","orderable": false },
            { "data": "growthRateA","orderable": false },
            { "data": "growthRateB","orderable": false },
            { "data": "nextStartTime","orderable": false },
            { "data": "voteStartTime","orderable": false },
            { "data": "voteEndTime","orderable": false },
            { "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
                    var result = '';
                    var a = '<a href="javascript:;" class="btn btn-default btn-sm J_change_score" data-guessId="'+data+'"data-scoreA="'+row.scoreA+'" data-scoreB="'+row.scoreB+'" data-couldGrantScore="'+row.couldGrantScore+'">修改比分</a>';
                    result += a;
                    if(row.grantStatus == 0){
                        var b = '<a href="javascript:;" class="btn btn-default btn-sm J_send_prize" data-guessId="'+data+'" data-scoreA="'+row.scoreA+'" data-scoreB="'+row.scoreB+'" data-couldGrantScore="'+row.couldGrantScore+'">发放奖励</a>';
                        result += b;
                    }
                    return result;
                }
            }
			    
        ],
        select: true,
        buttons: [
            /*{ extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }*/
        ]
    } );

    $modal = $('#publicModal');
    $("#event_guessing_list").on("click",".J_change_score",function(){
        var scoreA = $(event.target).attr("data-scoreA");
        var scoreB = $(event.target).attr("data-scoreB");
        var couldGrantScore = $(event.target).attr("data-couldGrantScore");
        if(couldGrantScore == scoreA || couldGrantScore == scoreB){
            bootbox.alert("赛事已结束，不能调整比分！");
            return;
        }
        $.ajax({
            url : "/rest/act/actactivityeventguessing/changeScore?guessId="+$(event.target).attr("data-guessId"),
            type : 'GET',
            success : function(data) {
                $modal.find(".modal-footer").hide();
                $modal.find(".modal-title").html("调整比分");
                $modal.find(".modal-body").html(data);
                $modal.modal('show');
            }
        });
    });

    $("#event_guessing_list").on("click",".J_send_prize",function(){
        var scoreA = $(event.target).attr("data-scoreA");
        var scoreB = $(event.target).attr("data-scoreB");
        var couldGrantScore = $(event.target).attr("data-couldGrantScore");
        if(couldGrantScore != scoreA && couldGrantScore != scoreB){
            bootbox.alert("赛事尚未结束，不能发放奖励");
            return;
        }
        $.ajax({
            url : "/rest/act/actactivityeventguessing/sendPrize?guessId="+$(event.target).attr("data-guessId"),
            type : 'GET',
            success : function(data) {
                $modal.find(".modal-footer").hide();
                $modal.find(".modal-title").html("发放奖励");
                $modal.find(".modal-body").html(data);
                $modal.modal('show');
            }
        });
    });
    
} );
