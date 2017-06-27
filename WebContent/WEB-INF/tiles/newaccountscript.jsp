<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpassword").keyup(checkPasswordsMatch);
		$("#details").submit(canSubmit);

	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password != confirmpassword) {

			showPasswordDontMatch();
			return false
		} else {

			showPasswordMatch();
			return true;
		}

	}

	function checkPasswordsMatch() {

		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password.length > 3 || confirmpassword.length > 3) {

			if (password == confirmpassword) {
				showPasswordMatch();
			} else {
				showPasswordDontMatch();
			}
		}

	}

	function showPasswordMatch() {
		$("#matchpass").text("<fmt:message key='Matched.user.password'/>");

		$("#matchpass").addClass("valid");
		$("#matchpass").removeClass("error");
	}

	function showPasswordDontMatch() {

		$("#matchpass").text(
				"<fmt:message key='UnmatchedPasswords.user.password'/>");

		$("#matchpass").addClass("error");
		$("#matchpass").removeClass("valid");
	}

	$(document).ready(onLoad);
</script>