package AES

import chisel3._
import chisel3.util._


class ShiftRows extends RawModule{
  val io = IO(new SR_IO)
  io.data_out := Cat(io.data_in(95,88),io.data_in(55,48),io.data_in(15,8),io.data_in(103,96),io.data_in(63,56),io.data_in(23,16),io.data_in(111,104),io.data_in(71,64),io.data_in(31,24) ,io.data_in(119,112),io.data_in(79,72),io.data_in(39,32),io.data_in(127,120),io.data_in(87,80),io.data_in(47,40),io.data_in(7,0))
}

object ShiftRows {
  def apply(datain:UInt) = {
    val SR = Module(new ShiftRows)
    SR.io.data_in := datain
    SR.io.data_out
  }
}
