package AES
import chisel3._
class XOR32 extends RawModule {
  val io = IO(new XOR_IO(32))
  io.data_out := io.data_in_1 ^ io.data_in_2
}
object XOR32 {
  def apply(data_in_1:UInt,data_in_2:UInt)= {
    val a = Module(new XOR32)
    a.io.data_in_1 := data_in_1
    a.io.data_in_2 := data_in_2
    a.io.data_out
  }
}

class XOR128 extends RawModule{
  val io = IO(new XOR_IO(128))
  io.data_out := io.data_in_1 ^ io.data_in_2
}
object XOR128 {
  def apply(data_in_1:UInt,data_in_2:UInt)= {
    val a = Module( new XOR128 )
    a.io.data_in_1 := data_in_1
    a.io.data_in_2 := data_in_2
    a.io.data_out
  }
}

class XOR8 extends RawModule{
  val io = IO(new XOR_IO(8))
  io.data_out := io.data_in_1 ^ io.data_in_2
}
object XOR8 {
  def apply(data_in_1:UInt,data_in_2:UInt)= {
    val a = Module(new XOR8)
    a.io.data_in_1 := data_in_1
    a.io.data_in_2 := data_in_2
    a.io.data_out
  }
}