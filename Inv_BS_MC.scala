package AES
import chisel3._
import chisel3.util._
import chisel3._
import chisel3.util._
import org.omg.CORBA.INVALID_TRANSACTION
//把T表分离出来
/*
class Inv_BS_MC extends RawModule{
  val io = IO(new BS_MC_IO)
  val t_table_d0 = VecInit(INV_t_table.inv_t_d0)
  val t_table_d1 = VecInit(INV_t_table.inv_t_d1)
  val t_table_d2 = VecInit(INV_t_table.inv_t_d2)
  val t_table_d3 = VecInit(INV_t_table.inv_t_d3)
  val c0_to_c3 = Wire(UInt(32.W))
  val c4_to_c7 = Wire(UInt(32.W))
  val c8_to_c11 = Wire(UInt(32.W))
  val c12_to_c15 = Wire(UInt(32.W))
  //每张表复制了四次 256项x4次x4张表  x32bit
  c0_to_c3 := t_table_d0(io.data_in(7,0)) ^ t_table_d1(io.data_in(15,8)) ^ t_table_d2(io.data_in(23,16)) ^ t_table_d3(io.data_in(31,24))
  c4_to_c7 := t_table_d0(io.data_in(39,32)) ^ t_table_d1(io.data_in(47,40)) ^ t_table_d2(io.data_in(55,48)) ^ t_table_d3(io.data_in(63,56))
  c8_to_c11 := t_table_d0(io.data_in(71,64)) ^ t_table_d1(io.data_in(79,72)) ^ t_table_d2(io.data_in(87,80)) ^ t_table_d3(io.data_in(95,88))
  c12_to_c15 := t_table_d0(io.data_in(103,96)) ^ t_table_d1(io.data_in(111,104)) ^ t_table_d2(io.data_in(119,112)) ^ t_table_d3(io.data_in(127,120))

  io.data_out := Cat(c12_to_c15(7,0),c12_to_c15(15,8),c12_to_c15(23,16),c12_to_c15(31,24),c8_to_c11(7,0),c8_to_c11(15,8),c8_to_c11(23,16),c8_to_c11(31,24),c4_to_c7(7,0),c4_to_c7(15,8),c4_to_c7(23,16),c4_to_c7(31,24),c0_to_c3(7,0),c0_to_c3(15,8),c0_to_c3(23,16),c0_to_c3(31,24))

}
*/
class Inv_BS_MC extends RawModule{
  val io = IO(new BS_MC_IO)
  val k0 = Wire(UInt(8.W))
  val k1 = Wire(UInt(8.W))
  val k2 = Wire(UInt(8.W))
  val k3 = Wire(UInt(8.W))
  val k4 = Wire(UInt(8.W))
  val k5 = Wire(UInt(8.W))
  val k6 = Wire(UInt(8.W))
  val k7 = Wire(UInt(8.W))
  val k8 = Wire(UInt(8.W))
  val k9 = Wire(UInt(8.W))
  val k10 = Wire(UInt(8.W))
  val k11 = Wire(UInt(8.W))
  val k12 = Wire(UInt(8.W))
  val k13 = Wire(UInt(8.W))
  val k14 = Wire(UInt(8.W))
  val k15 = Wire(UInt(8.W))
  val bs_data_out = Wire(UInt(128.W))
  bs_data_out := Inv_BS(io.data_in)

  k0 := ((GFMult(0x0E.U , bs_data_out(7,0)))^(GFMult(0x0B.U , bs_data_out(15,8)))^(GFMult(0x0D.U , bs_data_out(23,16)))^(GFMult(0x09.U , bs_data_out(31,24))))(7,0)
  k1 := ((GFMult(0x09.U , bs_data_out(7,0)))^(GFMult(0x0E.U , bs_data_out(15,8)))^(GFMult(0x0B.U , bs_data_out(23,16)))^(GFMult(0x0D.U , bs_data_out(31,24))))(7,0)
  k2 := ((GFMult(0x0D.U , bs_data_out(7,0)))^(GFMult(0x09.U , bs_data_out(15,8)))^(GFMult(0x0E.U , bs_data_out(23,16)))^(GFMult(0x0B.U , bs_data_out(31,24))))(7,0)
  k3 := ((GFMult(0x0B.U , bs_data_out(7,0)))^(GFMult(0x0D.U , bs_data_out(15,8)))^(GFMult(0x09.U , bs_data_out(23,16)))^(GFMult(0x0E.U , bs_data_out(31,24))))(7,0)

  k4 := ((GFMult(0x0E.U , bs_data_out(39,32)))^(GFMult(0x0B.U , bs_data_out(47,40)))^(GFMult(0x0D.U , bs_data_out(55,48)))^(GFMult(0x09.U , bs_data_out(63,56))))(7,0)
  k5 := ((GFMult(0x09.U , bs_data_out(39,32)))^(GFMult(0x0E.U , bs_data_out(47,40)))^(GFMult(0x0B.U , bs_data_out(55,48)))^(GFMult(0x0D.U , bs_data_out(63,56))))(7,0)
  k6 := ((GFMult(0x0D.U , bs_data_out(39,32)))^(GFMult(0x09.U , bs_data_out(47,40)))^(GFMult(0x0E.U , bs_data_out(55,48)))^(GFMult(0x0B.U , bs_data_out(63,56))))(7,0)
  k7 := ((GFMult(0x0B.U , bs_data_out(39,32)))^(GFMult(0x0D.U , bs_data_out(47,40)))^(GFMult(0x09.U , bs_data_out(55,48)))^(GFMult(0x0E.U , bs_data_out(63,56))))(7,0)

  k8  := ((GFMult(0x0E.U , bs_data_out(71,64)))^(GFMult(0x0B.U , bs_data_out(79,72)))^(GFMult(0x0D.U , bs_data_out(87,80)))^(GFMult(0x09.U , bs_data_out(95,88))))(7,0)
  k9  := ((GFMult(0x09.U , bs_data_out(71,64)))^(GFMult(0x0E.U , bs_data_out(79,72)))^(GFMult(0x0B.U , bs_data_out(87,80)))^(GFMult(0x0D.U , bs_data_out(95,88))))(7,0)
  k10 := ((GFMult(0x0D.U , bs_data_out(71,64)))^(GFMult(0x09.U , bs_data_out(79,72)))^(GFMult(0x0E.U , bs_data_out(87,80)))^(GFMult(0x0B.U , bs_data_out(95,88))))(7,0)
  k11 := ((GFMult(0x0B.U , bs_data_out(71,64)))^(GFMult(0x0D.U , bs_data_out(79,72)))^(GFMult(0x09.U , bs_data_out(87,80)))^(GFMult(0x0E.U , bs_data_out(95,88))))(7,0)

  k12 := ((GFMult(0x0E.U , bs_data_out(103,96)))^(GFMult(0x0B.U , bs_data_out(111,104)))^(GFMult(0x0D.U , bs_data_out(119,112)))^(GFMult(0x09.U , bs_data_out(127,120))))(7,0)
  k13 := ((GFMult(0x09.U , bs_data_out(103,96)))^(GFMult(0x0E.U , bs_data_out(111,104)))^(GFMult(0x0B.U , bs_data_out(119,112)))^(GFMult(0x0D.U , bs_data_out(127,120))))(7,0)
  k14 := ((GFMult(0x0D.U , bs_data_out(103,96)))^(GFMult(0x09.U , bs_data_out(111,104)))^(GFMult(0x0E.U , bs_data_out(119,112)))^(GFMult(0x0B.U , bs_data_out(127,120))))(7,0)
  k15 := ((GFMult(0x0B.U , bs_data_out(103,96)))^(GFMult(0x0D.U , bs_data_out(111,104)))^(GFMult(0x09.U , bs_data_out(119,112)))^(GFMult(0x0E.U , bs_data_out(127,120))))(7,0)

  io.data_out := Cat(k15,k14,k13,k12,k11,k10,k9,k8,k7,k6,k5,k4,k3,k2,k1,k0)
}
object Inv_BS_MC{
  def apply(data_in: UInt) = {
    val m = Module( new Inv_BS_MC)
    m.io.data_in := data_in
    m.io.data_out
  }
}

//把S表分离出来
class Inv_BS extends RawModule{
  val io = IO(new BS_MC_IO)
  val inv_s_table = VecInit(INV_s_table.inv_s)

  //s表直接复制了16次256x16 x8bit
  io.data_out := Cat(inv_s_table(io.data_in(127,120)),inv_s_table(io.data_in(119,112)),inv_s_table(io.data_in(111,104)),inv_s_table(io.data_in(103,96)),inv_s_table(io.data_in(95,88)),inv_s_table(io.data_in(87,80)),inv_s_table(io.data_in(79,72)),inv_s_table(io.data_in(71,64)),inv_s_table(io.data_in(63,56)),inv_s_table(io.data_in(55,48)),inv_s_table(io.data_in(47,40)),inv_s_table(io.data_in(39,32)),inv_s_table(io.data_in(31,24)),inv_s_table(io.data_in(23,16)),inv_s_table(io.data_in(15,8)),inv_s_table(io.data_in(7,0)))

}
object Inv_BS{
  def apply(data_in: UInt) = {
    val m = Module( new Inv_BS)
    m.io.data_in := data_in
    m.io.data_out
  }
}
