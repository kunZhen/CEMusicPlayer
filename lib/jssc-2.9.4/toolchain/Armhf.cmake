set(CMAKE_SYSTEM_NAME Linux)
set(TOOLCHAIN_PREFIX arm-linux-gnueabi)

message(STATUS "Note: Some compilers may require version information to be found")
# set(COMPILER_VERSION "-4.7")

set(TOOLCHAIN_SUFFIX hf)
set(CMAKE_C_COMPILER ${TOOLCHAIN_PREFIX}${TOOLCHAIN_SUFFIX}-gcc${COMPILER_VERSION})
set(CMAKE_CXX_COMPILER ${TOOLCHAIN_PREFIX}${TOOLCHAIN_SUFFIX}-g++${COMPILER_VERSION})
set(CMAKE_STRIP ${TOOLCHAIN_PREFIX}${TOOLCHAIN_SUFFIX}-strip CACHE FILEPATH "" FORCE)
set(CMAKE_FIND_ROOT_PATH /usr/${TOOLCHAIN_PREFIX}${TOOLCHAIN_SUFFIX}/)
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY BOTH)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE BOTH)

