package Bean;
public class Sessao {
	private static int cod_user;
	private static String senha_user;
	private static String nome_user;
	private static String funcao_func;
	private static String nome_func;
	private static String re_func;
	private static String email_func;
	private static String status_func;
	public static int getCod_user() {
		return cod_user;
	}
	public static void setCod_user(int cod_user) {
		Sessao.cod_user = cod_user;
	}
	public static String getSenha_user() {
		return senha_user;
	}
	public static void setSenha_user(String senha_user) {
		Sessao.senha_user = senha_user;
	}
	public static String getNome_user() {
		return nome_user;
	}
	public static void setNome_user(String nome_user) {
		Sessao.nome_user = nome_user;
	}
	public static String getFuncao_func() {
		return funcao_func;
	}
	public static void setFuncao_func(String funcao_func) {
		Sessao.funcao_func = funcao_func;
	}
	public static String getNome_func() {
		return nome_func;
	}
	public static void setNome_func(String nome_func) {
		Sessao.nome_func = nome_func;
	}
	public static String getRe_func() {
		return re_func;
	}
	public static void setRe_func(String re_func) {
		Sessao.re_func = re_func;
	}
	public static String getEmail_func() {
		return email_func;
	}
	public static void setEmail_func(String email_func) {
		Sessao.email_func = email_func;
	}
	public static String getStatus_func() {
		return status_func;
	}
	public static void setStatus_func(String status_func) {
		Sessao.status_func = status_func;
	}
	
}
