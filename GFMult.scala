package AES

import chisel3._
import chisel3.util._

class GFMult extends RawModule{
  val io = IO(new GFMULT_IO)
  val m = Wire(UInt(11.W))
  m :=  ((Cat(0.U(3.W),io.data_in_1)) & (Cat(io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0),io.data_in_2(0)))) ^
        ((Cat(0.U(2.W),io.data_in_1,0.U(1.W))) & (Cat(io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1),io.data_in_2(1)))) ^
        ((Cat(0.U(1.W),io.data_in_1,0.U(2.W))) & (Cat(io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2),io.data_in_2(2)))) ^
        ((Cat(io.data_in_1,0.U(3.W))) & (Cat(io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3),io.data_in_2(3))))
  io.data_out := m(7,0)^(("b00011011".U) & (Cat(m(8),m(8),m(8),m(8),m(8),m(8),m(8),m(8))) )^(("b00110110".U) & (Cat(m(9),m(9),m(9),m(9),m(9),m(9),m(9),m(9)))) ^ (("b01101100".U) & (Cat(m(10),m(10),m(10),m(10),m(10),m(10),m(10),m(10))))
}

object GFMult{
  def apply(coe:UInt,data_in:UInt) = {
    val a = Module(new GFMult)
    a.io.data_in_1 := data_in
    a.io.data_in_2 := coe
    a.io.data_out
  }
}
