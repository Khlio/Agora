/* Created by jankoatwarpspeed.com */

(function($) {
    $.fn.formToWizard = function(options) {
        options = $.extend({
            submitButton: ""
        }, options);

        var element = this;

        var steps = $(element).find("fieldset");
        var count = steps.size();
        var submmitButtonName = "#" + options.submitButton;
        $(submmitButtonName).hide();

        // 2
        $(element).before("<div id ='containerStep' class='container'></div>");
        $("#containerStep").append("<div id='rowStep' class='row'></ul>");
        $("#rowStep").append("<ul id='steps' class='text-center'></ul>");
        steps.each(function(i) {
            $(this).wrap("<div id='steps" + i + "'></div>");
            $(this).append("<p class='PaddingEsc' id='steps" + i + "commands'></p>");

            // 2
            var name = $(this).find("legend").html();
            $("#steps").append("<li id='stepDesc" + i + "'class='col-md-3 col-lg-3 col-xs-3'>Etape " + (i + 1) + "<span class=''>" + name + "</span></li>");

            if (i == 0) {
                createNextButton(i);
                selectStep(i);
            }
            else if (i == count - 1) {
                $("#steps" + i).hide();
                createPrevButton(i);
            }
            else {
                $("#steps" + i).hide();
                createPrevButton(i);
                createNextButton(i);
            }
        });

        function createPrevButton(i) {
            var stepName = "steps" + i;
            $("#" + stepName + "commands").append("<a href='#' id='" + stepName + "Prev' class='btn btn-default btn-xs pull-left ButtonForm'>< Pr&eacute;c&eacute;dent</a>");

            $("#" + stepName + "Prev").bind("click", function(e) {
                $("#" + stepName).hide();
                $("#steps" + (i - 1)).show();
                $(submmitButtonName).hide();
                selectStep(i - 1);
            });
        }

        function createNextButton(i) {
            var stepName = "steps" + i;
            $("#" + stepName + "commands").append("<a href='#' id='" + stepName + "Next' class='btn btn-default btn-xs pull-right'>Suivant ></a>");

            $("#" + stepName + "Next").bind("click", function(e) {
                $("#" + stepName).hide();
                $("#steps" + (i + 1)).show();
                if (i + 2 == count)
                    $(submmitButtonName).show();
                selectStep(i + 1);
            });
        }

        function selectStep(i) {
            $("#steps li").removeClass("current");
            $("#stepDesc" + i).addClass("current");
        }
    };
})(jQuery); 