var TableEditable = function () {

    return {

        //main function to initiate the module
        init: function () {

            var oTable = $('#sample_editable_2').dataTable({
                "bFilter": false,
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource": "user/getUserInfo",
                "fnServerParams": function ( aoData ) {
                    aoData.push( { "name": "more_data", "value": "my_value" } );
                },
                "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
                    oSettings.jqXHR = $.ajax( {
                        "dataType": 'json',
                        "type": "POST",
                        "url": sSource,
                        "data": aoData,
                        "success": fnCallback
                    } );
                },
                "aoColumns": [
                    { "sTitle": "userName","mData": "userName"},
                    {"sTitle": "fullName", "mData": "fullName"},
                    { "sTitle": "points","mData": "points"},
                    { "sTitle": "notes","mData": "notes"}
                ],
                "aLengthMenu": [
                    [5, 15, 20],
                    [5, 15, 20] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,

                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "当前数据为从第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前页",
                        "sNext": "后页",
                        "sLast": "尾页"
                    }
                },
                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [2,3]
                }
                ]
            });

            //jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass("form-control input-medium"); // modify table search input
            //jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            //jQuery('#sample_editable_1_wrapper .dataTables_length select').select2({
            //    showSearchInput: false //hide search box with special css class
            //}); // initialize select2 dropdown


        }

    };

}();