package com.example.gestionipn.utils

object BotResponse {
    fun basicResponses(_message:String):String{
        val valor = 0
        val message=_message.toLowerCase()

        return when{

            //examen
            message.contains("Examen")->{
                when (valor){
                    0->"Las fechas de los examenes \n" +
                            "estan en el siguiente enlace\n" +
                            "https://www.ipn.mx/assets/files/cecyt9/docs/4-estudiantes/avisos/3er_corte_2022-1.pdf"
                    else->"error"
                }
            }

            //Dictamen
            message.contains("Dictamen")->{
                when (valor){
                    0->"Actualmente no cuento con iformacion sobre dictamenes \n" +
                            "Puedes pedir mas informacion en el siguiente correo\n" +
                            "gestion.escolar.cecyt9@gmail.com"
                    else->"error"
                }
            }
            //Becas
            message.contains("Becas")->{
                when (valor){
                    0->"Para saber tu situacion sobre tu beca\n" +
                            "Consulta en uno de los siguientes enlaces\n" +
                            "https://www.ipn.mx/assets/files/cecyt9/docs/4-estudiantes/becas/ListadoApp.pdf\n" +
                            "https://www.ipn.mx/assets/files/cecyt9/docs/4-estudiantes/becas/ListadoPendientes.pdf"
                    else->"error"
                }
            }
            else->{
                when (valor){
                    0->"No te puedo ayudar con eso"
                    else->"Error"
                }
            }
        }
    }
}