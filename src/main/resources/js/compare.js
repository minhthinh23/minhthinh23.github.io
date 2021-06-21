$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "ajax/countryName/filler",
        data: {},
        dataType: "json",
        success: function (data) {
            html = '';
            for (let i = 0; i < data.length; i++) {
                html = html + `<option value='` + i + `'>` + data[i] + `</option>`;
            }

            $("#country_1").html("<option value=''>Country 1</option>"+html);
            $("#country_2").html("<option value=''>Country 2</option>"+html);
        }
    });

    $("#country_1").change(function () {
        cou1();
    });

    $("#country_2").change(function () {
        cou2();
    });

    function cou1() {
        var url;
        if ($("#date_begin").val() && $("#date_end").val()) {
            url = "ajax/country/" + $("#country_1").val() + " " + $("#date_begin").val().replace(/\//g, '') + "&" + $("#date_end").val().replace(/\//g, '')
        } else {
            url = "ajax/country/" + $("#country_1").val()
        }

        $.ajax({
            type: "GET",
            url: url,
            data: {},
            dataType: "json",
            success: function (data) {
                $("#country_Name_1").html($("option[value=" + $("#country_1").val() + "]").html());
                $("#country_TCases_1").html(data.cases);
                $("#country_TDeath_1").html(data.death);
                $("#country_PerCap_1").html(data.capita);
                $("#country_Mort_1").html(data.mortality);
                $("#country_MaxD_1").html(data.maxDead);
            }
        });
    }
    function cou2() {
        var url;
        if ($("#date_begin").val() && $("#date_end").val()) {
            url = "ajax/country/" + $("#country_2").val() + " " + $("#date_begin").val().replace(/\//g, '') + "&" + $("#date_end").val().replace(/\//g, '')
        } else {
            url = "ajax/country/" + $("#country_2").val()
        }

        $.ajax({
            type: "GET",
            url: url,
            data: {},
            dataType: "json",
            success: function (data) {
                $("#country_Name_2").html($("option[value=" + $("#country_2").val() + "]").html());
                $("#country_TCases_2").html(data.cases);
                $("#country_TDeath_2").html(data.death);
                $("#country_PerCap_2").html(data.capita);
                $("#country_Mort_2").html(data.mortality);
                $("#country_MaxD_2").html(data.maxDead);
            }
        });
    };

    $("#date_end").focusout(function () { 
        setTimeout(function(){
            if ($("#country_1").val() && $("#country_2").val()) {
                cou1();cou2();
            }else{
                if($("#country_1").val()){
                    cou1();
                }else if($("#country_2").val()){
                    cou2();
                }
            }
        }, 100);
    });
    
});