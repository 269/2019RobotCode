import pixy 
from ctypes import *
from pixy import *

# Pixy2 Python SWIG get blocks example #

print ("Pixy2 Python SWIG Example -- Get Blocks")

pixy.init ()
pixy.change_prog ("color_connected_components");

class Blocks (Structure):
  _fields_ = [ ("m_signature", c_uint),
    ("m_x", c_uint),
    ("m_y", c_uint),
    ("m_width", c_uint),
    ("m_height", c_uint),
    ("m_angle", c_uint),
    ("m_index", c_uint),
    ("m_age", c_uint) ]

blocks = BlockArray(100)
frame = 0
curX = []
while 1:
  curX = []
  count = pixy.ccc_get_blocks (100, blocks)

  if count > 0:
    
    frame = frame + 1
    for index in range (0, count):
      curX.append(blocks[index].m_x)
    try:
      findError = (curX[0] + curX[1])/2
      print(findError)
    except:
      pass
    
